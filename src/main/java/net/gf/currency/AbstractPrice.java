package net.gf.currency;

import java.util.Currency;

abstract class AbstractPrice implements Price {
	private final double value;
	private final Currency currency;
	
	protected AbstractPrice (double value, Currency currency) {
		this.value = value;
		this.currency = currency;
	}
	
	@Override
	public double getValue() {
		return value;
	}

	@Override
	public double getUSValue(float toUSDollarRatio) {
		return value * toUSDollarRatio;
	}

	@Override
	public Currency getCurrency() {
		return currency;
	}

	@Override
	public String toString() {
		return "AbstractPrice [value=" + value + ", currency=" + currency + "]";
	}
}
