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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currency == null) ? 0 : currency.getCurrencyCode().hashCode());
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractPrice other = (AbstractPrice) obj;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.getCurrencyCode().equals(other.currency.getCurrencyCode()))
			return false;
		if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
			return false;
		return true;
	}
	
	
}
