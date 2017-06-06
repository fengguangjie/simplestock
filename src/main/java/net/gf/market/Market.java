package net.gf.market;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Market holds stocks and trades.
 * @author gf
 *
 */
public final class Market {
	private final Map<Stock, List<Trade>> stockTrades 
								= new HashMap<Stock, List<Trade>>();
		
	public static Market getInstance(){
		return LazyHolder.INSTANCE;
	}
	
	private final ReadWriteLock lock;
	
	private Market(){
		lock = new ReentrantReadWriteLock();
	}
	
	public void addTrade(Trade trade) throws MarketException {
		if (trade.getQuantity() < 1)
			throw new MarketException(trade + " quantity is not greater than 1");
		
		final Lock wlock = lock.writeLock();
		
		wlock.lock();
		
		try {
			final Stock s = trade.getStock();
			
			List<Trade> array = stockTrades.get(s);
			if (array == null){
				array = new ArrayList<Trade>();
				
				stockTrades.put(s, array);
			}
			
			array.add(trade);
			
		} finally{
			wlock.unlock();
		}
	}
	
	public Map<Stock, List<Trade>> getAllTrades(){
		final Lock rlock = lock.readLock();
		
		rlock.lock();
		try {
			final Map<Stock, List<Trade>> ret 
						= new HashMap<Stock, List<Trade>>();
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
