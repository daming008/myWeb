package com.dm.bookstore.domain;

public class ShoppingCartItem {
	Book book = null;
	int quantity = 1;
	
	public ShoppingCartItem(Book book) {
		this.book = book;
	}
	
	public Book getBook() {
		return book;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public float getItemMoney() {
		return book.getPrice() * quantity;
	}
	
	public void increment() {
		quantity ++;
	}
}
