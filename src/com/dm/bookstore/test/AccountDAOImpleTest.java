package com.dm.bookstore.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dm.bookstore.dao.AccountDAO;
import com.dm.bookstore.dao.impl.AccountDAOImple;
import com.dm.bookstore.domain.Account;

public class AccountDAOImpleTest {
	AccountDAO accountDAO = new AccountDAOImple();

	@Test
	public void testGet() {
		Account a  = accountDAO.get(1);
		System.out.println(a);
	}

	@Test
	public void testUpdateBalance() {
		accountDAO.updateBalance(1, 100);
	}

}
