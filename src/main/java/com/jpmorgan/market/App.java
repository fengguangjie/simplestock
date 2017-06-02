package com.jpmorgan.market;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import net.gf.currency.Price;
import net.gf.currency.UKSterling;
import net.gf.market.Market;
import net.gf.market.Stock;
import net.gf.market.StockType;
import net.gf.market.Trade;
import net.gf.market.TradeType;
import net.gf.service.MarketCalculatorService;
import net.gf.service.SimpleCalculator;

public class App {
	private final Market market;
	private final MarketCalculatorService service;

	private final List<Stock<UKSterling>> stocks = new ArrayList<Stock<UKSterling>>();
	
	public App() throws Exception {
		market = Market.getInstance();
		service = new SimpleCalculator(market);
	
		Stock<UKSterling> stock = new Stock<UKSterling>("TEA", StockType.COMMON, 
				new UKSterling(0), new Double(-1), new UKSterling(100));
		stocks.add(stock);
		
		stock = new Stock<UKSterling>("POP", StockType.COMMON, 
				new UKSterling(8), new Double(-1), new UKSterling(100));
		stocks.add(stock);
		
		stock = new Stock<UKSterling>("ALE", StockType.COMMON, 
				new UKSterling(23), new Double(-1), new UKSterling(60));
		stocks.add(stock);
		
		stock = new Stock<UKSterling>("GIN", StockType.PREFERRED, 
				new UKSterling(8), 0.02, new UKSterling(100));
		stocks.add(stock);
		
		stock = new Stock<UKSterling>("JOE", StockType.COMMON, 
				new UKSterling(13), new Double(-1), new UKSterling(250));
		stocks.add(stock);
	}
	
	public void trading() throws Exception {
		final Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			final Stock<UKSterling> s = stocks.get(r.nextInt(stocks.size()));
			final TradeType tt = TradeType.values()[r.nextInt(TradeType.values().length)];
			
			final Trade<UKSterling> t = new Trade<UKSterling>(
					s, tt,
					new UKSterling(r.nextDouble()*10 + 0.1),
					r.nextInt(999) + 1, 
					System.currentTimeMillis()
					);
			
			market.addTrade(t);
			
			Thread.sleep(100);
		}
	}
	
	public void process() throws Exception {
		final Random r = new Random();
		
		for(Stock<UKSterling> stock : stocks) {
			final Price price = new UKSterling(r.nextDouble()*10);
			System.out.println("Stock Symbol: " + stock.getStockSymbol() 
					+ " at price " + price.getValue() 
					+ " in " + price.getCurrency().getDisplayName());
			System.out.println("\tDividend Yield: " +  service.getDividendYield(stock, price));
			System.out.println("\tP/E Ratio: " + service.getPERatio(stock, price));
		}
		
		System.out.println();
		
		final Price gm = service.getGeometricMean();
		System.out.println("Geometric Mean: " + gm.getValue() + " in " + gm.getCurrency().getDisplayName());
		
		final long now = System.currentTimeMillis();
		final long fifteenMinutes = TimeUnit.MINUTES.toMillis(15);
		
		final Price vwsp = service.getVolumeWeightStockPrice(now, fifteenMinutes);
		System.out.println("Volume Weighted Stock Price: " + vwsp.getValue() + " in " + vwsp.getCurrency().getDisplayName());
	}

	public static void main(String[] args) throws Exception {
		final App app = new App();
		app.trading();
		app.process();
	}

}
