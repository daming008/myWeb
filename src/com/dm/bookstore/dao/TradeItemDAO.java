package com.dm.bookstore.dao;

import java.util.Collection;
import java.util.Set;

import com.dm.bookstore.domain.TradeItem;

public interface TradeItemDAO {

	/**
	 * ��������TradeItem����
	 * @param items
	 */
	public abstract void batchSave(Collection<TradeItem> items);
	
	/**
	 * ����TradeId��ȡ���Ӧ��TradeItem�ļ��� 
	 * @param TradeId
	 * @return
	 */
	public abstract Set<TradeItem> getTradeItemsWithTradeId(Integer TradeId);
	
}
