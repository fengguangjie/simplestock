package net.gf.currency;

import java.util.Currency;
import java.util.Locale;

/**
 * Price in US dollar. See {@link Price}.
 * @author gf
 *
 */
public final class USDollar extends AbstractPrice {
	public static final String currencyCode = Currency.getInstance(Locale.US).getCurrencyCode();
	
	public USDollar(double value) {
		super(value, Currency.getInstance(Locale.US));
	}
	
	@Override
	public double getUSValue(float toUSDollarRatio) {
		return getValue();
	}
}
