package net.gf.market;

import net.gf.currency.Price;

public final class PreferredStock extends Stock {
	
	private final double fixedDividend;
	
	public PreferredStock(String stockSymbol, Price lastDividend, Price parValue, double fixedDividend) {
		super(stockSymbol, lastDividend, parValue);
		
		this.fixedDividend = fixedDividend;
	}
	@Override
	public strictfp double getDividendYield(Price price) {
		if (price.getValue() == 0)
			throw new RuntimeException(price + " value is zero.");
		
		return (fixedDividend * getParValue().getValue())/price.getValue();
	}

}
