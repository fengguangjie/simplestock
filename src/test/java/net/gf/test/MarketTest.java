package net.gf.test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import net.gf.currency.PriceException;
import net.gf.currency.UKSterling;
import net.gf.market.CommonStock;
import net.gf.market.Market;
import net.gf.market.MarketException;
import net.gf.market.PreferredStock;
import net.gf.market.Stock;
import net.gf.market.Trade;
import net.gf.market.TradeType;
import net.gf.service.MarketCalculatorService;
import net.gf.service.MarketCalculatorServiceException;
import net.gf.service.SimpleCalculator;

public class MarketTest {
	Market market;
	MarketCalculatorService service;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void setup(){
		market = Market.getInstance();
		service = new SimpleCalculator();
	}

	@Test
	public void calcuatorServiceExceptionTest() {
		Stock stock = new CommonStock("TEA",  
				new UKSterling(0), new UKSterling(100));
		
		try {
			service.getPERatio(stock, new UKSterling(2));
		} catch (MarketCalculatorServiceException e) {
			assertTrue("catch exception.", true);
		}
		
	}
	
	@Test
	public void test() throws MarketException, PriceException, MarketCalculatorServiceException {
		Stock stock1 = new CommonStock("TEA",  
				new UKSterling(0), new UKSterling(100));
		long timestamp1 = System.currentTimeMillis();
		Trade trade1 = new Trade(stock1, TradeType.BUY, new UKSterling(8.3),
				800, timestamp1);
		
		
		Stock stock2 = new CommonStock("POP",  
				new UKSterling(8), new UKSterling(100));
		long timestamp2 = timestamp1 + 1020;
		Trade trade2 = new Trade(stock2, TradeType.BUY, new UKSterling(6.3),
				680, timestamp2);

		
		Stock stock3 = new CommonStock("ALE", 
				new UKSterling(23), new UKSterling(60));
		long timestamp3 = timestamp1 + 1200;
		Trade trade3 = new Trade(stock3, TradeType.BUY, new UKSterling(6.8),
				80, timestamp3);
		
		Stock stock4 = new PreferredStock("GIN", 
				new UKSterling(8), new UKSterling(100), 0.02d);
		long timestamp4 = timestamp1 + 1350;
		Trade trade4 = new Trade(stock4, TradeType.BUY, new UKSterling(18.3),
				30, timestamp4);
		
		Stock stock5 = new CommonStock("JOE",  
				new UKSterling(13), new UKSterling(250));
		long timestamp5 = timestamp1 + 1520;
		Trade trade5 = new Trade(stock5, TradeType.BUY, new UKSterling(8.38),
				1800, timestamp5);
		
		long timestamp6 = timestamp1 + 1820;
		Trade trade6 = new Trade(stock5, TradeType.BUY, new UKSterling(8.8),
				50, timestamp6);
		
		market.addTrade(trade1);
		market.addTrade(trade2);
		market.addTrade(trade3);
		market.addTrade(trade4);
		market.addTrade(trade5);
		market.addTrade(trade6);
		
		Map<Stock, List<Trade>> allTrades =market.getAllTrades();
		
		assertEquals(5, allTrades.size());
		
		assertEquals(1, allTrades.get(stock1).size());
		assertEquals(trade1, allTrades.get(stock1).get(0));
		
		assertEquals(1, allTrades.get(stock2).size());
		assertEquals(trade2, allTrades.get(stock2).get(0));
		
		assertEquals(1, allTrades.get(stock3).size());
		assertEquals(trade3, allTrades.get(stock3).get(0));
		
		assertEquals(1, allTrades.get(stock4).size());
		assertEquals(trade4, allTrades.get(stock4).get(0));
		
		assertEquals(2, allTrades.get(stock5).size());
		assertEquals(trade5, allTrades.get(stock5).get(0));
		assertEquals(trade6, allTrades.get(stock5).get(1));
		
		assertEquals(8.8481d, 
				service.getGeometricMean(allTrades).getValue(), 0.0001d);
		
		assertEquals(8.0061, service.getVolumeWeightStockPrice(allTrades, 
				timestamp6, 2000).getValue(), 0.0001d);
		assertEquals(8.5495, service.getVolumeWeightStockPrice(allTrades, 
				timestamp6, 500).getValue(), 0.0001d);
	}
}
