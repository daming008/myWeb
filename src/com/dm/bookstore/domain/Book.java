package com.dm.bookstore.domain;

import java.util.Date;

public class Book {
	private int id;
	private String author;
	private String title;
	private long price;
	private Date publishingDate;
	private long salesAmount;
	private int storeNumber;
	private String remark;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public Date getPublishingDate() {
		return publishingDate;
	}
	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}
	public long getSalesAmount() {
		return salesAmount;
	}
	public void setSalesAmount(long salesAmount) {
		this.salesAmount = salesAmount;
	}
	public int getStoreNumber() {
		return storeNumber;
	}
	public void setStoreNumber(int storeNumber) {
		this.storeNumber = storeNumber;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", author=" + author + ", title=" + title
				+ ", price=" + price + ", publishingDate=" + publishingDate
				+ ", salesAmount=" + salesAmount + ", storeNumber="
				+ storeNumber + ", remark=" + remark + "]";
	}
	
	
	
}
