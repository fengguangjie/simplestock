package net.gf.market;

import net.gf.currency.Price;

/**
 * Holds a stock information.
 * @author gf
 *
 * @param <T> locale price
 */
public abstract class Stock {

	private final String stockSymbol;

	private Price lastDividend;

	private Price parValue;

	protected Stock(String stockSymbol, Price lastDividend, Price parValue) {
		
		this.stockSymbol = stockSymbol;
		this.lastDividend = lastDividend;
		this.parValue = parValue;
	}

	public Price getLastDividend() {
		return this.lastDividend;
	}


	public Price getParValue() {
		
		return this.parValue;
	}

	public String getStockSymbol() {
		
		return this.stockSymbol;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stockSymbol == null) ? 0 : stockSymbol.hashCode());
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
		Stock other = (Stock) obj;
		if (stockSymbol == null) {
			if (other.stockSymbol != null)
				return false;
		} else if (!stockSymbol.equals(other.stockSymbol))
			return false;
		return true;
	}
	
	public abstract double getDividendYield(final Price price);
}
