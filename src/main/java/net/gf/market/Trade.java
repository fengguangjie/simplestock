package net.gf.market;

import net.gf.currency.Price;

/**
 * Holds a trade entered to {@link Market} with {@link Stock}.
 * @author gf
 *
 * @param  locale {@link Price}
 */
public final class Trade implements Comparable<Long> {
	private final Stock stock;
	private final TradeType tradeType;
	private final Price price;
	private final long quantity;
	private final long timestamp;

	public Trade(Stock stock, TradeType tradeType, Price price, long quantity, long timestamp) {
		this.stock = stock;
		this.tradeType = tradeType;
		this.price = price;
		this.quantity = quantity;
		this.timestamp = timestamp;
	}

	public Stock getStock() {
		return this.stock;
	}

	public long getTimestamp() {
		return this.timestamp;
	}

	public long getQuantity() {
		return this.quantity;
	}

	public TradeType getTradeType() {
		return this.tradeType;
	}

	public Price getPrice() {
		return this.price;
	}

	@Override
	public int compareTo(Long o) {
		final long thisTimeStamp = this.timestamp;
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
