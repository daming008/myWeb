package com.dm.bookstore.test;

import java.sql.Connection;

import org.junit.Test;

import com.dm.bookstore.db.JDBCUtils;
import com.dm.bookstore.web.ConnectionContext;

public class TestConnection {
	
	@Test
	public void con(){
		Connection connection = JDBCUtils.getConnection();
		ConnectionContext.getInstance().bind(connection);
		for(int i=0;i<200;i++){
			System.out.println(ConnectionContext.getInstance().get()+"::"+i);
		}
	}
}
