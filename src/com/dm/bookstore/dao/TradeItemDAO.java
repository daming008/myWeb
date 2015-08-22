package com.dm.bookstore.dao;

import java.util.Collection;
import java.util.Set;

import com.dm.bookstore.domain.TradeItem;

public interface TradeItemDAO {

	/**
	 * 批量保存TradeItem对象
	 * @param items
	 */
	public abstract void batchSave(Collection<TradeItem> items);
	
	/**
	 * 根据TradeId获取其对应的TradeItem的集合 
	 * @param TradeId
	 * @return
	 */
	public abstract Set<TradeItem> getTradeItemsWithTradeId(Integer TradeId);
	
}
