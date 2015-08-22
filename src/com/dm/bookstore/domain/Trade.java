package com.dm.bookstore.domain;

import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

public class Trade {
	private int tradeId;
	private int userid;
	private Date tradetime;
	
	private Set<TradeItem> items = new LinkedHashSet<>();

	public int getTradeId() {
		return tradeId;
	}

	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Date getTradetime() {
		return tradetime;
	}

	public void setTradetime(Date tradetime) {
		this.tradetime = tradetime;
	}

	public Set<TradeItem> getItems() {
		return items;
	}

	public void setItems(Set<TradeItem> items) {
		this.items = items;
	}

	
	


	@Override
	public String toString() {
		return "Trade [tradeId=" + tradeId + ", userid=" + userid
				+ ", tradetime=" + tradetime + "]";
	}
	
	
	
}
