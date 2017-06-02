package net.gf.market;

import net.gf.currency.Price;
import net.gf.market.StockType;

/**
 * Holds a stock information.
 * @author gf
 *
 * @param <T> locale price
 */
public final class Stock<T extends Price> {

	private final String stockSymbol;

	private final StockType stockType;

	private T lastDividend;

	private final double fixedDividend;

	private T parValue;

	public Stock(String stockSymbol, StockType stockType, T lastDividend, Double fixedDividend,
			T parValue) throws StockException {
		if (stockType == StockType.PREFERRED && fixedDividend.doubleValue() == -1)
			throw new StockException("Preferred type stock should define fixedDividend.");
		
		this.stockSymbol = stockSymbol;
		this.stockType = stockType;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
	}

	public double getFixedDividend() {
		return this.fixedDividend;
	}

	public T getLastDividend() {
		return this.lastDividend;
	}


	public T getParValue() {
		
		return this.parValue;
	}

	public String getStockSymbol() {
		
		return this.stockSymbol;
	}

	public StockType getStockType() {
		
		return this.stockType;
	}

	public void setLastDividend(T lastDividend) {
		this.lastDividend = lastDividend;

	}

	public void setParValue(T parValue) {
		this.parValue = parValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stockSymbol == null) ? 0 : stockSymbol.hashCode());
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock<T> other = (Stock<T>) obj;
		if (stockSymbol == null) {
			if (other.stockSymbol != null)
				return false;
		} else if (!stockSymbol.equals(other.stockSymbol))
			return false;
		return true;
	}
}
