package com.dm.bookstore.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Set;

import org.junit.Test;

import com.dm.bookstore.dao.TradeDAO;
import com.dm.bookstore.dao.impl.TradeDAOImpl;
import com.dm.bookstore.domain.Trade;

public class TradeFDAOTest {

	@Test
	public void testInsertTrade() {
//		TradeDAO tradeDAO = new TradeDAOImpl();
//		Trade trade = new Trade(2,null);
//		tradeDAO.insert(trade);
	}

	@Test
	public void testGetTradesWithUserId() {
		TradeDAO tradeDAO = new TradeDAOImpl();
		Set<Trade> tradeWithTradeId = tradeDAO.getTradesWithUserId(1);
		System.out.println(tradeWithTradeId);
	}

}
