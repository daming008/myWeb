package com.dm.bookstore.web;

import java.sql.Connection;


public class ConnectionContext {
	private ConnectionContext(){
		
	}
	private static ConnectionContext instance = new ConnectionContext();
	
	public static ConnectionContext getInstance(){
		return instance;
	}
	ThreadLocal<Connection> connectionContext = new ThreadLocal<>();
	
	public void bind(Connection connection){
		connectionContext.set(connection);
	}
	
	public Connection get(){
		return connectionContext.get();
	}
	
	public void remove(){
		connectionContext.remove();
	}
}
