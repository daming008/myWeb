package com.dm.bookstore.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import com.dm.bookstore.dao.impl.BaseDAO;
import com.dm.bookstore.dao.impl.BookDAOImpl;
import com.dm.bookstore.db.JDBCUtils;
import com.dm.bookstore.domain.Book;
import com.dm.bookstore.utils.ReflectionUtils;

public class BaseDAOTest {
	private BookDAOImpl bookDAOImpl = new BookDAOImpl();
	
	@Test
	public void testInsert() {
		String sql = "insert into trade (userid,tradetime) values(?,?)";
		long id = bookDAOImpl.insert(sql, 2,new Date(new java.util.Date().getTime()));
		Class clazz = ReflectionUtils.getSuperGenericType(getClass());
		System.out.println(clazz);
	}

	@Test
	public void testUpdate() {

		Connection connection = null;
		try {
			connection = JDBCUtils.getConnection();
			String sql = "UPDATE mybooks SET Author=? where id=?";
			bookDAOImpl.update(sql, "LDM",1);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.release(connection);
		}
		
	}

	@Test
	public void testQuery() {
		//System.out.println(Integer.MAX_VALUE);
		String sql = "SELECT id,author,title,price,publishingDate,salesAmount,storeNumber,remark from mybooks where id = ?";
		Book book = bookDAOImpl.query(sql, 1);
		System.out.println(book);
	}

	@Test
	public void testQueryForList() {
		String sql = "SELECT id,author,title,price,publishingDate,salesAmount,storeNumber,remark from mybooks";
		List<Book> books = bookDAOImpl.queryForList(sql);
		for(Book b : books){
			System.out.println(b);
		}
	}

	@Test
	public void testGetSingleVal() {
		String sql = "SELECT count(id) FROM mybooks";
		Long count = bookDAOImpl.getSingleVal(sql);
		System.out.println(count);
	}

	@Test
	public void testBatch() {
		String sql = "UPDATE mybooks SET salesAmount=?,storenumber=? where id=?";
		bookDAOImpl.batch(sql, new Object[]{1,1,1},new Object[]{2,2,5},new Object[]{3,3,3});
	}

	
	
}
