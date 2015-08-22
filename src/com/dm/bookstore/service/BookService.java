package com.dm.bookstore.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import com.dm.bookstore.dao.AccountDAO;
import com.dm.bookstore.dao.BookDAO;
import com.dm.bookstore.dao.TradeDAO;
import com.dm.bookstore.dao.TradeItemDAO;
import com.dm.bookstore.dao.UserDAO;
import com.dm.bookstore.dao.impl.AccountDAOImple;
import com.dm.bookstore.dao.impl.BookDAOImpl;
import com.dm.bookstore.dao.impl.TradeDAOImpl;
import com.dm.bookstore.dao.impl.TradeItemDAOImpl;
import com.dm.bookstore.dao.impl.UserDAOImpl;
import com.dm.bookstore.domain.Book;
import com.dm.bookstore.domain.ShoppingCart;
import com.dm.bookstore.domain.ShoppingCartItem;
import com.dm.bookstore.domain.Trade;
import com.dm.bookstore.domain.TradeItem;
import com.dm.bookstore.web.CirteriaBook;
import com.dm.bookstore.web.Page;

public class BookService {
	BookDAO bookDAO = new BookDAOImpl();
	public Page<Book> getPage(CirteriaBook cirteriaBook){
		return bookDAO.getPage(cirteriaBook);
	}
	public Book getBook(int id) {
		return bookDAO.getBook(id);
	}
	public boolean addToCart(int id, ShoppingCart sc) {
		Book book = bookDAO.getBook(id);
		if(book != null){
			sc.addBook(book);
			return true;
		}
		return false;
	}
	public void removeItemFromShoppingCart(int id, ShoppingCart sc) {
		sc.removeItem(id);
	}
	public void clearShoppingCart(ShoppingCart sc) {
		sc.clear();
	}
	public void updateItemQuantity(ShoppingCart sc, int id, int quantity) {
		sc.updateItemQuantity(id, quantity);
		
	}
	public static void cash(ShoppingCart shoppingCart, String username, String accountID) {
		//获取各种DAO
		BookDAO bookDAO = new BookDAOImpl();
		TradeDAO tradeDao = new TradeDAOImpl();
		UserDAO userDAO = new UserDAOImpl();
		TradeItemDAO tradeItemDAO = new TradeItemDAOImpl();
		AccountDAO accountDAO = new AccountDAOImple();
		
		//跟新mybook表中的storeNumber 和 salesAmount
		bookDAO.batchUpdateStoreNumberAndSalesAmount(shoppingCart.getItems());
		 int i = 10 / 0;
		//更新account表
		accountDAO.updateBalance(Integer.parseInt(accountID), shoppingCart.getTotalMoney());
		//向trade表中插入数据
		Trade trade = new Trade();
		trade.setTradetime(new Date(new java.util.Date().getTime()));
		trade.setUserid(userDAO.getUser(username).getUserId());
		tradeDao.insert(trade);
		
		//向tradeItem表插入数据
		Collection<TradeItem> items = new ArrayList<>();
		for(ShoppingCartItem sc:shoppingCart.getItems()){
			TradeItem tradeItem = new TradeItem();
			tradeItem.setBookId(sc.getBook().getId());
			tradeItem.setQuantity(sc.getQuantity());
			tradeItem.setTradeId(trade.getTradeId());
			
			items.add(tradeItem);
		}
		tradeItemDAO.batchSave(items);
		
		//清空购物车
		shoppingCart.clear();
	}
}
