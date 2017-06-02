package net.gf.market;

public class StockException extends Exception {
	private static final long serialVersionUID = 1678043347618948128L;

	public StockException(String error) {
		super(error);
	}
}
