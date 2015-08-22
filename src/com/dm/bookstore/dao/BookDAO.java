package com.dm.bookstore.dao;

import java.util.Collection;
import java.util.List;

import com.dm.bookstore.domain.Book;
import com.dm.bookstore.domain.ShoppingCartItem;
import com.dm.bookstore.web.CirteriaBook;
import com.dm.bookstore.web.Page;

public interface BookDAO {
	
	/**
	 * 根据id获取指定Book对象
	 * @param id
	 * @return
	 */
	public abstract Book getBook(int id);
	
	/**
	 * 获取传入的CirteriaBook对象返回其对应的Page 对象
	 * @param cb
	 * @return
	 */
	public abstract Page<Book> getPage(CirteriaBook cb);
	
	//根据传入的CirteraBook对象返回其对应的记录数
	public abstract long getTotalBookNumber(CirteriaBook cb);
	
	//根据传入的CirteriaBook 和pageSize返回当前页对应的List
	public abstract List<Book> getPageList(CirteriaBook cb,int pageSize);
	
	//返回指定id的 book的storeNumber字段的值
	public abstract int getStoreNumber(Integer id);
	
	//根据传入的ShoppingCartItem的集合，批量更新books数据表的storenumerou 和salsesnumber字段的值
	public abstract void batchUpdateStoreNumberAndSalesAmount(Collection<ShoppingCartItem> items);
}
