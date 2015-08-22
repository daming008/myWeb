package com.dm.bookstore.service;

import com.dm.bookstore.dao.AccountDAO;
import com.dm.bookstore.dao.impl.AccountDAOImple;
import com.dm.bookstore.domain.Account;

public class AccountService {
	private AccountDAO accountDAO = new AccountDAOImple();
	
	public Account getAccount(Integer accountID){
		return accountDAO.get(accountID);
	}
}
