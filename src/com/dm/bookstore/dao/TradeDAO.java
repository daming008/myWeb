package com.dm.bookstore.dao;

import java.util.Set;

import com.dm.bookstore.domain.Trade;

public interface TradeDAO {
	
	/**
	 * �򔵓����в���Trade����
	 * @param items
	 */
	public abstract void insert(Trade trade);
	
	/**
	 * ����userId��ȡ���������Trade����
	 * @param tradeId
	 * @return
	 */
	
	public abstract  Set<Trade> getTradesWithUserId(Integer userId);
}
