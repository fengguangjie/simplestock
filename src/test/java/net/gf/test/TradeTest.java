package net.gf.test;

import org.junit.Test;
import static org.junit.Assert.*;

import net.gf.currency.Price;
import net.gf.currency.UKSterling;
import net.gf.market.CommonStock;
import net.gf.market.Stock;
import net.gf.market.Trade;
import net.gf.market.TradeType;

public class TradeTest {

	@Test
	public void test() {
		long now = System.currentTimeMillis();
		
		Stock stock = new CommonStock("TEA",  
				new UKSterling(6), new UKSterling(100));
		
		Price price = new UKSterling(8.63d);
		
		Trade trade = new Trade(stock, TradeType.BUY, price, 7787, now);
		
		assertEquals(price, trade.getPrice());
		assertEquals(7787, trade.getQuantity());
		assertEquals(TradeType.BUY, trade.getTradeType());
		assertEquals(now, trade.getTimestamp());
		assertEquals(stock, trade.getStock());
	}

}
