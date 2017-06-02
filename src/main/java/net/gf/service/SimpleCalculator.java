package net.gf.service;

import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.gf.currency.PriceHelper;
import net.gf.currency.Price;
import net.gf.currency.PriceException;
import net.gf.market.Market;
import net.gf.market.Stock;
import net.gf.market.StockType;
import net.gf.market.Trade;

/**
 * A kind of {@link MarketCalculatorService}.
 * @author gf
 *
 */
public final class SimpleCalculator implements MarketCalculatorService {
	
	private final Market market;
	
	public SimpleCalculator(Market market) {
		this.market = market;
	}

	@Override
	public Price getVolumeWeightStockPrice(final long now, final long pastDuration) throws MarketCalculatorServiceException, PriceException {
		
		final Map<Stock<? extends Price>, List<Trade<? extends Price>>> trades = market.getAllTrades();
		
		double ret = 0;
		long quantity = 0;
		Currency currency = null;
		
		for (List<Trade<? extends Price>> list : trades.values()){
			for (Trade<? extends Price> trade : list) {
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
	public Price getGeometricMean() throws PriceException {
		final Map<Stock<? extends Price>, List<Trade<? extends Price>>> trades = market.getAllTrades();
		
		double ret = 1;
		long n = 1;
		Currency currency = null;
		
		for (List<Trade<? extends Price>> list : trades.values()){
			for (Trade<? extends Price> trade : list) {
				if (currency == null)
					currency = trade.getPrice().getCurrency();
				
				ret = Math.pow(ret, (n - 1)/(double)n) * Math.pow(trade.getPrice().getValue(), 1/(double)n);
				
				n++;
			}
		}
		
		return PriceHelper.lookupFromCurrency(ret, currency);
	}

	@Override
	public double getDividendYield(final Stock<? extends Price> stock, final Price price) throws MarketCalculatorServiceException {
		if (price.getValue() <= 0)
			throw new MarketCalculatorServiceException(stock + " Price is not larger than 0");
		
		if (stock.getStockType() == StockType.COMMON) {
			return stock.getLastDividend().getValue()/price.getValue();
		} else if (stock.getStockType() == StockType.PREFERRED) {
			return (stock.getFixedDividend() * stock.getParValue().getValue())/price.getValue();
		} else
			throw new MarketCalculatorServiceException(stock + " unknown stock type.");
	}

	@Override
	public double getPERatio(final Stock<? extends Price> stock, final Price price) throws MarketCalculatorServiceException {
		if (stock.getLastDividend().getValue() == 0)
			return -1;
		
		return price.getValue()/stock.getLastDividend().getValue();
	}

}
