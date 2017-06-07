package net.gf.service;

import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.gf.currency.Price;
import net.gf.currency.PriceException;
import net.gf.currency.PriceHelper;
import net.gf.market.Stock;
import net.gf.market.Trade;

/**
 * A kind of {@link MarketCalculatorService}.
 * @author gf
 *
 */
public final class SimpleCalculator implements MarketCalculatorService {
	
	public SimpleCalculator() {
	}

	@Override
	public Price getVolumeWeightStockPrice(final Map<Stock, List<Trade>> trades, final long now, final long pastDuration) throws MarketCalculatorServiceException, PriceException {
		
		double ret = 0;
		long quantity = 0;
		Currency currency = null;
		
		for (List<Trade> list : trades.values()){
			for (Trade trade : list) {
				if ((now - trade.getTimestamp()) <= pastDuration) {
					if (currency == null)
						currency = trade.getPrice().getCurrency();
				
					ret = (quantity*ret) + trade.getPrice().getValue() * trade.getQuantity();
					quantity += trade.getQuantity();
					
					ret = ret/(double)quantity;
				}
			}
		}
	
		if (currency == null) {
			ret = -1;
			currency = Currency.getInstance(Locale.getDefault());
		}
		
		return PriceHelper.lookupFromCurrency(ret, currency);
	}

	@Override
	public Price getGeometricMean(final Map<Stock, List<Trade>> trades) throws PriceException {
		
		double ret = 1;
		long n = 1;
		Currency currency = null;
		
		for (List<Trade> list : trades.values()){
			for (Trade trade : list) {
				if (currency == null)
					currency = trade.getPrice().getCurrency();
				
				ret = Math.pow(ret, (n - 1)/(double)n) * Math.pow(trade.getPrice().getValue(), 1/(double)n);
				
				n++;
			}
		}
		
		return PriceHelper.lookupFromCurrency(ret, currency);
	}

	@Override
	public double getDividendYield(final Stock stock, final Price price) throws MarketCalculatorServiceException {
		if (price.getValue() <= 0)
			throw new MarketCalculatorServiceException(stock + " Price is not larger than 0");
		
		return stock.getDividendYield(price);
	}

	@Override
	public double getPERatio(final Stock stock, final Price price) throws MarketCalculatorServiceException {
		if (price.getValue() <= 0)
			throw new MarketCalculatorServiceException(stock + " Price is not larger than 0");
		
		return price.getValue()/stock.getLastDividend().getValue();
	}

}
