package net.gf.market;

import net.gf.currency.Price;

public final class PreferredStock extends Stock {
	
	private final double fixedDividend;
	
	public PreferredStock(String stockSymbol, Price lastDividend, Price parValue, double fixedDividend) {
		super(stockSymbol, lastDividend, parValue);
		
		this.fixedDividend = fixedDividend;
	}
	@Override
	public double getDividendYield(Price price) {
		return (fixedDividend * getParValue().getValue())/price.getValue();
	}

}
