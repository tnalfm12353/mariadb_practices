package com.douzone.bookmall.vo;

public class OrderBookVo {

	private Long bookId;
	private String bookTitle;
	
	private int count;
	private int price;
	
	private Long orderId;
	private int orderNum;

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "OrderBookVo [bookId=" + bookId + ", bookTitle=" + bookTitle + ", count=" + count + ", price=" + price
				+ ", orderNum=" + orderNum + "]";
	}

	
}
