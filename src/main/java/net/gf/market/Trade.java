package net.gf.market;

import net.gf.currency.Price;

/**
 * Holds a trade entered to {@link Market} with {@link Stock}.
 * @author gf
 *
 * @param <T> locale {@link Price}
 */
public final class Trade<T extends Price> implements Comparable<Long> {
	private final Stock<T> stock;
	private final TradeType tradeType;
	private final T price;
	private final long quantity;
	private final Long timestamp;

	public Trade(Stock<T> stock, TradeType tradeType, T price, long quantity, Long timestamp) {
		this.stock = stock;
		this.tradeType = tradeType;
		this.price = price;
		this.quantity = quantity;
		this.timestamp = timestamp;
	}

	public Stock<T> getStock() {
		return this.stock;
	}

	public Long getTimestamp() {
		return this.timestamp;
	}

	public long getQuantity() {
		return this.quantity;
	}

	public TradeType getTradeType() {
		return this.tradeType;
	}

	public T getPrice() {
		return this.price;
	}

	@Override
	public int compareTo(Long o) {
		final long thisTimeStamp = this.timestamp.longValue();
		final long otherTimeStamp = o.longValue();
		if (thisTimeStamp > otherTimeStamp)
			return 1;
		else if(thisTimeStamp < otherTimeStamp)
			return -1;
		else
			return 0;
	}

	@Override
	public String toString() {
		return "Trade [stock=" + stock + ", tradeType=" + tradeType + ", price=" + price + ", quantity=" + quantity
				+ ", timestamp=" + timestamp + "]";
	}
}
