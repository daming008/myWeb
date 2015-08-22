package com.dm.bookstore.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.dm.bookstore.dao.impl.BookDAOImpl;
import com.dm.bookstore.domain.Book;
import com.dm.bookstore.domain.ShoppingCartItem;
import com.dm.bookstore.service.BookService;
import com.dm.bookstore.web.BookStoreWebUtils;
import com.dm.bookstore.web.CirteriaBook;
import com.dm.bookstore.web.Page;

public class BookDAOImplTest {

	BookDAOImpl book = new BookDAOImpl();
	CirteriaBook cb = new CirteriaBook(50, 60, 20);
	@Test
	public void testGetBook() {
		Book b = book.getBook(10);
		System.out.println(b);
	}

	@Test
	public void testGetPage() {
		Page<Book> page = book.getPage(cb);
		System.out.println(page.getList());
		System.out.println("¹²"+page.getTotalPageNumber()+"Ò³");
		System.out.println(page.getNextPage());
		System.out.println(page.getPrevPage());
	}

	@Test
	public void testGetTotalBookNumber() {
		long num = book.getTotalBookNumber(cb);
		System.out.println(num);
	}

	@Test
	public void testGetPageList() {
		List<Book> books = book.getPageList(cb, 4);
		for(Book book:books){
			System.out.println(book);
			
		}
	}

	@Test
	public void testGetStoreNumber() {
		int storeNumber = book.getStoreNumber(5);
		System.out.println(storeNumber);
	}

	@Test
	public void testBatchUpdateStoreNumberAndSalesAmount() {
		BookService bookService = new BookService();
		Collection<ShoppingCartItem> items = new ArrayList<>();
		//Book b1 = bookService.getBook(1);
		Book b1 =book.getBook(1);
		ShoppingCartItem sci = new ShoppingCartItem(b1);
		sci.setQuantity(10);
		items.add(sci);
		
		b1 = bookService.getBook(2);
		sci = new ShoppingCartItem(b1);
		sci.setQuantity(10);
		items.add(sci);
		
		book.batchUpdateStoreNumberAndSalesAmount(items);
	}

}
