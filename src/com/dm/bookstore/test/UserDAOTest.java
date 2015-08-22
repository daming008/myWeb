package com.dm.bookstore.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dm.bookstore.dao.UserDAO;
import com.dm.bookstore.dao.impl.UserDAOImpl;
import com.dm.bookstore.domain.User;

public class UserDAOTest {

	@Test
	public void testGetUser() {
		UserDAO userDAO = new UserDAOImpl();
		User user = userDAO.getUser("AAA");
		System.out.println(user);
	}

}
