package net.gf.test;

import org.junit.Test;

import static org.junit.Assert.*;

import net.gf.currency.Price;
import net.gf.currency.UKSterling;
import net.gf.market.CommonStock;
import net.gf.market.PreferredStock;
import net.gf.market.Stock;

public class StockTest {

	@Test
	public void commonStockTest() {
		Stock stock = new CommonStock("TEA",  
				new UKSterling(6), new UKSterling(100));
		
		Price price = new UKSterling(8.63d);
		
		assertEquals("TEA", stock.getStockSymbol());
		assertEquals(new UKSterling(6), stock.getLastDividend());
		assertEquals(new UKSterling(100), stock.getParValue());
		
		assertEquals(0.69525, stock.getDividendYield(price), 0.0001d);
	}
	
	@Test
	public void preferredtockTest() {
		Stock stock = new PreferredStock("GIN", 
				new UKSterling(6), new UKSterling(100), 0.02d);
		
		Price price = new UKSterling(8.63d);
		
		assertEquals("GIN", stock.getStockSymbol());
		assertEquals(new UKSterling(6), stock.getLastDividend());
		assertEquals(new UKSterling(100), stock.getParValue());
		
		assertEquals(0.23175, stock.getDividendYield(price), 0.0001d);

	}
}
