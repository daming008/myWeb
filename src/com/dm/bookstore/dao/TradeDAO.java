package com.dm.bookstore.dao;

import java.util.Set;

import com.dm.bookstore.domain.Trade;

public interface TradeDAO {
	
	/**
	 * 向數據表中插入Trade对象
	 * @param items
	 */
	public abstract void insert(Trade trade);
	
	/**
	 * 根据userId获取和其关联的Trade集合
	 * @param tradeId
	 * @return
	 */
	
	public abstract  Set<Trade> getTradesWithUserId(Integer userId);
}
