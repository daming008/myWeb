package com.dm.bookstore.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.dm.bookstore.dao.BookDAO;
import com.dm.bookstore.domain.Book;
import com.dm.bookstore.domain.ShoppingCartItem;
import com.dm.bookstore.web.CirteriaBook;
import com.dm.bookstore.web.Page;

public class BookDAOImpl extends BaseDAO<Book> implements BookDAO {

	@Override
	public Book getBook(int id) {
		String sql = "SELECT id,author,title,price,publishingDate,salesAmount,"
				+ "storeNumber,remark from mybooks where id = ?";
		return query(sql, id);
	}

	@Override
	public Page<Book> getPage(CirteriaBook cb) {
		Page<Book> page = new Page<>(cb.getPageNo());
		page.setTotalItemNumber(getTotalBookNumber(cb));
		cb.setPageNo(page.getPageNo());
		page.setList(getPageList(cb, 3));
		return page;
	}

	@Override
	public long getTotalBookNumber(CirteriaBook cb) {
		String sql = "SELECT count(id) from mybooks where price >= ? and price <= ?";
		return getSingleVal(sql, cb.getMinPrice(),cb.getMaxPrice());
	}

	@Override
	public List<Book> getPageList(CirteriaBook cb, int pageSize) {
		String sql = "SELECT id,author,title,price,publishingDate,salesAmount,"
				+ "storeNumber,remark from mybooks where price >= ? and price <= ? limit ?,?";
		return queryForList(sql, cb.getMinPrice(),cb.getMaxPrice(),(cb.getPageNo()-1) * pageSize,pageSize);
	}

	@Override
	public int getStoreNumber(Integer id) {
		String sql = "SELECT storenumber from mybooks where id=?";
		return getSingleVal(sql, id);
	}

	@Override
	public void batchUpdateStoreNumberAndSalesAmount(
			Collection<ShoppingCartItem> items) {
		String sql = "update mybooks set storenumber = storenumber - ? , salesamount = salesamount + ? where id = ?";
		Object params[][] = null;
		List<ShoppingCartItem> scis = new ArrayList<>(items);
		params = new Object[items.size()][3]; 
		
		for(int i = 0;i<items.size();i++){
			params[i][0] = scis.get(i).getQuantity();
			params[i][1] = scis.get(i).getQuantity();
			params[i][2] = scis.get(i).getBook().getId();
		}
		
		batch(sql, params);
	}

}
