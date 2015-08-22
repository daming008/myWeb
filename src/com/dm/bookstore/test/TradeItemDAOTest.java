package com.dm.bookstore.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.junit.Test;

import com.dm.bookstore.dao.TradeItemDAO;
import com.dm.bookstore.dao.impl.TradeItemDAOImpl;
import com.dm.bookstore.domain.TradeItem;

public class TradeItemDAOTest {

	TradeItemDAO tradeItemDAO = new TradeItemDAOImpl();
	@Test
	public void testBatchSave() {
		Collection<TradeItem> items = new ArrayList<>();
		TradeItem item = new TradeItem(null, 3, 10, 16);
		items.add(item);
		
		TradeItem item2 = new TradeItem(null, 4, 18, 16);
		items.add(item);
		
		tradeItemDAO.batchSave(items);
	}

	@Test
	public void testGetTradeItemsWithTradeId() {
		Set<TradeItem> items = tradeItemDAO.getTradeItemsWithTradeId(15);
		System.out.println(items);
	}

}
