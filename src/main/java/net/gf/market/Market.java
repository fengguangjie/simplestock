package net.gf.market;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import net.gf.currency.Price;

/**
 * Market holds stocks and trades.
 * @author gf
 *
 */
public final class Market {
	private final Map<Stock<? extends Price>, List<Trade<? extends Price>>> stockTrades 
								= new HashMap<Stock<? extends Price>, List<Trade<? extends Price>>>();
		
	public static Market getInstance(){
		return LazyHolder.INSTANCE;
	}
	
	private final ReadWriteLock lock;
	
	private Market(){
		lock = new ReentrantReadWriteLock();
	}
	
	public void addTrade(Trade<? extends Price> trade) throws MarketException {
		if (trade.getQuantity() < 1)
			throw new MarketException(trade + " quantity is not greater than 1");
		
		final Lock wlock = lock.writeLock();
		
		wlock.lock();
		
		try {
			final Stock<? extends Price> s = trade.getStock();
			
			List<Trade<? extends Price>> array = stockTrades.get(s);
			if (array == null){
				array = new ArrayList<Trade<? extends Price>>();
				
				stockTrades.put(s, array);
			}
			
			array.add(trade);
			
		} finally{
			wlock.unlock();
		}
	}
	
	public Map<Stock<? extends Price>, List<Trade<? extends Price>>> getAllTrades(){
		final Lock rlock = lock.readLock();
		
		rlock.lock();
		try {
			final Map<Stock<? extends Price>, List<Trade<? extends Price>>> ret 
						= new HashMap<Stock<? extends Price>, List<Trade<? extends Price>>>();
			ret.putAll(stockTrades);
			return ret;
		} finally{
			rlock.unlock();
		}
	}
	
	 private static class LazyHolder {
        private static final Market INSTANCE = new Market();
    }
}
