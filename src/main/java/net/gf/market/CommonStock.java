package net.gf.market;

import net.gf.currency.Price;

public final class CommonStock extends Stock {

	public CommonStock(String stockSymbol, Price lastDividend, Price parValue) {
		super(stockSymbol, lastDividend, parValue);
	}
	
	@Override
	public double getDividendYield(Price price) {
		return getLastDividend().getValue()/price.getValue();
	}
}
