package com.dm.bookstore.dao.impl;

import com.dm.bookstore.dao.UserDAO;
import com.dm.bookstore.domain.User;

public class UserDAOImpl extends BaseDAO<User> implements UserDAO{

	@Override
	public User getUser(String username) {
		String sql = "select userId,username,accountId from userinfo where username = ?";
		return query(sql, username);
	}
	
}
