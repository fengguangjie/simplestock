package net.gf.currency;

import java.util.Currency;
import java.util.Locale;

/**
 * Price in UK sterling. See {@link Price}.
 * @author gf
 *
 */
public final class UKSterling extends AbstractPrice {
	public static final String currencyCode = Currency.getInstance(Locale.UK).getCurrencyCode();
	
	public UKSterling(double value) {
		super(value, Currency.getInstance(Locale.UK));
	}
}
