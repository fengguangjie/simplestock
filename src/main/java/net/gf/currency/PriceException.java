package net.gf.currency;

public class PriceException extends Exception {
	private static final long serialVersionUID = 3872308964850432972L;

	public PriceException(String error) {
		super(error);
	}
}
