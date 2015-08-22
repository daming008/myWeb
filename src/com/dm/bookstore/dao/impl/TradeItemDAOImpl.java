package com.dm.bookstore.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dm.bookstore.dao.TradeItemDAO;
import com.dm.bookstore.domain.TradeItem;

public class TradeItemDAOImpl extends BaseDAO<TradeItem> implements TradeItemDAO {

	@Override
	public void batchSave(Collection<TradeItem> items) {
		String sql = "insert into tradeitem(bookid,quantity,tradeid) values(?,?,?)";
		Object[][] params = new Object[items.size()][3];
		List<TradeItem> traItems = new ArrayList<>(items);
		for(int i=0;i<items.size();i++){
			params[i][0]=traItems.get(i).getBookId();
			params[i][1]=traItems.get(i).getQuantity();
			params[i][2]=traItems.get(i).getTradeId();
		}
		batch(sql, params);
	}

	@Override
	public Set<TradeItem> getTradeItemsWithTradeId(Integer tradeId) {
		String sql = "select itemid tradeItemId,bookid,quantity,tradeid from tradeitem where tradeid = ? ";
		return new  HashSet<>(queryForList(sql, tradeId));
	}

}
