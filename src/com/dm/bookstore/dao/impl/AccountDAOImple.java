package com.dm.bookstore.dao.impl;

import com.dm.bookstore.dao.AccountDAO;
import com.dm.bookstore.domain.Account;

public class AccountDAOImple extends BaseDAO<Account> implements AccountDAO {

	@Override
	public Account get(Integer accountId) {
		String sql = "select accountId,balance from account where accountId = ? ";
		return query(sql, accountId);
	}

	@Override
	public void updateBalance(Integer accountId, float amount) {
		String sql = "update account set balance = balance - ? where accountId = ?";
		update(sql, amount,accountId);
	}
	
}
