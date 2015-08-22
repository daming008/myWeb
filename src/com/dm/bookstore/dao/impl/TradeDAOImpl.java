package com.dm.bookstore.dao.impl;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.dm.bookstore.dao.TradeDAO;
import com.dm.bookstore.domain.Trade;

public class TradeDAOImpl extends BaseDAO<Trade> implements TradeDAO{

	@Override
	public void insert(Trade trade) {
		String sql = "insert into trade(userid,tradetime) values(?,?)";
		long tradeID = insert(sql, trade.getUserid(),trade.getTradetime());
		trade.setTradeId((int)tradeID);
	}

	@Override
	public Set<Trade> getTradesWithUserId(Integer userId) {
		String sql = "select tradeId,userid,tradetime from trade where userid = ?";
		return  new HashSet<Trade>(queryForList(sql, userId));
	}

}
