package com.dm.bookstore.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class ShoppingCart {
	private Map<Integer, ShoppingCartItem> books = new HashMap<Integer, ShoppingCartItem>();
	
	/**
	 * 修改指定项的数量
	 * @param booksID
	 * @param quantity
	 */
	public void updateItemQuantity(Integer booksID, Integer quantity){
		ShoppingCartItem sci = books.get(booksID);
		books.get(1);
		if(sci != null){
			sci.setQuantity(quantity);
		}
	}
	
	public void removeItem(Integer id){
		books.remove(id);
	}
	
	public void clear(){
		books.clear();
	}
	
	public boolean isEmpty(){
		return books.isEmpty();
	}
	
	public float getTotalMoney(){
		float totalMoney = 0;
		for(ShoppingCartItem sci:getItems()){
			totalMoney += sci.getItemMoney();
		}
		return totalMoney;
	}
	
	public Collection<ShoppingCartItem> getItems(){
		return books.values();
	}
	
	public int getBookNumber(){
		int total = 0;
		
		for(ShoppingCartItem sci : books.values()){
			total += sci.getQuantity();
		}
		
		return total;
	}
	
	public Map<Integer, ShoppingCartItem> getBooks() {
		return books;
	}
	
	public boolean hasBook(Integer id){
		return books.containsKey(id);
	}
	
	public void addBook(Book book) {
		ShoppingCartItem sci = books.get(book.getId());
		if(sci == null){
			sci = new ShoppingCartItem(book);
			books.put(book.getId(), sci);
		}else{
			sci.increment();
		}
	}
}
