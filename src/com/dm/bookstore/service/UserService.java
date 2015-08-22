package com.dm.bookstore.service;

import com.dm.bookstore.dao.UserDAO;
import com.dm.bookstore.dao.impl.UserDAOImpl;
import com.dm.bookstore.domain.User;

public class UserService {

	UserDAO userDAO = new UserDAOImpl();
	public User getUserByUserName(String userName){
		return userDAO.getUser(userName);
	}
	
}
