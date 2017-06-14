package net.gf.market;

import net.gf.currency.Price;

public final class CommonStock extends Stock {

	public CommonStock(String stockSymbol, Price lastDividend, Price parValue) {
		super(stockSymbol, lastDividend, parValue);
	}
	
	@Override
	public strictfp double getDividendYield(Price price) {
		if (price.getValue() == 0)
			throw new RuntimeException(price + " value is zero.");
		
		return getLastDividend().getValue()/price.getValue();
	}
}
