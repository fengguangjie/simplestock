package net.gf.currency;

import java.util.Currency;

/**
 * A helper for a {@link Price} instance from {@link Currency}.
 * @author gf
 *
 */
public class PriceHelper {
	public static Price lookupFromCurrency(double value, Currency currency) throws PriceException {
		if(UKSterling.currencyCode.equals(currency.getCurrencyCode()))
			return new UKSterling(value);
		else if (USDollar.currencyCode.equals(currency.getCurrencyCode()))
			return new USDollar(value);
		else
			throw new PriceException(currency + " Price has not implemented yet");
	}
}
