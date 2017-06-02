package net.gf.service;

import net.gf.currency.Price;
import net.gf.currency.PriceException;
import net.gf.market.Stock;

/**
 * Calculation service for the {@link net.gf.market.Market}. 
 * @author gf
 *
 */
public interface MarketCalculatorService {
	
	public double getDividendYield(Stock<? extends Price> stock, Price price) throws MarketCalculatorServiceException;
	
	public double getPERatio(Stock<? extends Price> stock, Price price) throws MarketCalculatorServiceException;
	
	public Price getVolumeWeightStockPrice(long now, long pastDuration) throws MarketCalculatorServiceException, PriceException;
	
	public Price getGeometricMean() throws PriceException;
}
