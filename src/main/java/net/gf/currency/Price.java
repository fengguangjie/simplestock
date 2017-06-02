package net.gf.currency;

import java.util.Currency;
/**
 * {@link Trade} price in locale {@link Currency}.
 * @author gf
 *
 */
public interface Price {
	/**
	 * The price value
	 * @return double
	 */
	public double getValue();
	
	/**
	 * Price in locale currency.
	 * @return Currency
	 */
	public Currency getCurrency();
	
	/**
	 * Convert price in locale currency to US dollar.
	 * @param toUSDollarRatio float
	 * @return double
	 */
	public double getUSValue(float toUSDollarRatio);
}
