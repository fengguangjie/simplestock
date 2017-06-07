package net.gf.service;

import java.util.List;
import java.util.Map;

import net.gf.currency.Price;
import net.gf.currency.PriceException;
import net.gf.market.Stock;
import net.gf.market.Trade;

/**
 * Calculation service for the {@link net.gf.market.Market}. 
 * @author gf
 *
 */
public interface MarketCalculatorService {
	
	public double getDividendYield(Stock stock, Price price) throws MarketCalculatorServiceException;
	
	public double getPERatio(Stock stock, Price price) throws MarketCalculatorServiceException;
	
	public Price getVolumeWeightStockPrice(final Map<Stock, List<Trade>> trades,long now, long pastDuration) throws MarketCalculatorServiceException, PriceException;
	
	public Price getGeometricMean(final Map<Stock, List<Trade>> trades) throws PriceException;
}
