package com.dm.bookstore.dao;

import com.dm.bookstore.domain.User;


public interface UserDAO {

	/**
	 * �����û�����ȡ User ����
	 * @param username
	 * @return
	 */
	public abstract User getUser(String username);

}

