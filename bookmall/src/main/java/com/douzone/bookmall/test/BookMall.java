package com.douzone.bookmall.test;

import java.util.List;

import com.douzone.bookmall.dao.BookDao;
import com.douzone.bookmall.dao.CartDao;
import com.douzone.bookmall.dao.CategoryDao;
import com.douzone.bookmall.dao.MemberDao;
import com.douzone.bookmall.dao.OrderBookDao;
import com.douzone.bookmall.dao.OrderDao;
import com.douzone.bookmall.vo.BookVo;
import com.douzone.bookmall.vo.CartVo;
import com.douzone.bookmall.vo.CategoryVo;
import com.douzone.bookmall.vo.MemberVo;
import com.douzone.bookmall.vo.OrderBookVo;
import com.douzone.bookmall.vo.OrderVo;

public class BookMall {

	public static void main(String[] args) {
		line("회원");
		insertMember("김재홍","010-2222-2222","asdfasdf@google.com","asdfsdgsd");
		insertMember("오둘리","010-2332-6666","aseasdf@naver.com","asgsd12354");
		
		List<MemberVo> memberList = new MemberDao().findAll();
		displayInfo(memberList);
		
		line("카테고리");
		insertCategory("IT");
		insertCategory("인문");
		insertCategory("소설");
		
		List<CategoryVo> categoryList= new CategoryDao().findAll();
		displayInfo(categoryList);
		
		line("서적");
		insertBook("이것이 자바다",50000, categoryList.get(0));
		insertBook("스프링 인 액션",30000, categoryList.get(2));
		
		List<BookVo> bookList = new BookDao().findAll();
		displayInfo(bookList);
		
		line("카트");
		insertCart(2, memberList.get(0), bookList.get(1));
		insertCart(1, memberList.get(1), bookList.get(1));
		
		List<CartVo> cartList = new CartDao().findAll();
		displayInfo(cartList);
		
		line("주문");
		insertOrder(20210521, "부산 센텀시티", memberList.get(0));
		
		List<OrderVo> orderList = new OrderDao().findAll();
		displayInfo(orderList);
		
//		line("주문 서적");
//		insertOrderBook(3, bookList.get(0), orderList.get(0));
//		insertOrderBook(1, bookList.get(1), orderList.get(0));
//		
//		List<OrderBookVo> orderBookList = new OrderBookDao().findAll();
//		displayInfo(orderBookList);
		
		
	}
	/**
	 * 	insert
	 * 
	 */
	private static void insertMember(String name, String tel, String email, String pw) {
		MemberVo vo = new MemberVo();
		vo.setName(name);
		vo.setTel(tel);
		vo.setEmail(email);
		vo.setPassword(pw);
		Boolean result = new MemberDao().insert(vo);
		displayRegister(name, " 님이", result);
	}
	
	private static void insertBook(String title, int price, CategoryVo categoryVo) {
		BookVo vo = new BookVo();
		vo.setTitle(title);
		vo.setPrice(price);
		vo.setCategoryId(categoryVo.getId());
		Boolean result = new BookDao().insert(vo);
		displayRegister(title, " 서적이", result);
	}

	private static void insertCategory(String category) {
		CategoryVo vo = new CategoryVo();
		vo.setCategory(category);
		Boolean result = new CategoryDao().insert(vo);;
		displayRegister(category, " 카테고리가", result);
	}
	
	private static void insertCart(int count, MemberVo memberVo, BookVo bookVo) {
		CartVo vo = new CartVo();
		vo.setCount(count);
		vo.setMemberId(memberVo.getId());
		vo.setBookId(bookVo.getId());
		Boolean result = new CartDao().insert(vo);
		displayRegister(memberVo.getName(), "님 장바구니에"+bookVo.getTitle()+"서적이 ", result);
	}

	private static void insertOrderBook(int count, BookVo bookVo, OrderVo orderVo) {
		OrderBookVo vo = new OrderBookVo();
		vo.setBookId(bookVo.getId());
		vo.setPrice(bookVo.getPrice() - 2000); // 2000원 할인된 가격으로 구매
		vo.setCount(count);
		vo.setOrderId(orderVo.getId());
		Boolean result = new OrderBookDao().insert(vo);
		displayRegister(bookVo.getTitle(), "를 주문 서적에", result);
	}
	
	private static void insertOrder(int orderNum, String address, MemberVo memberVo) {
		OrderVo vo = new OrderVo();
		vo.setOrderNum(orderNum);
		vo.setTotalPrice(0);
		vo.setAddress(address);
		vo.setMemberId(memberVo.getId());
		Boolean result = new OrderDao().insert(vo);
		displayRegister(Integer.toString(orderNum), " : 주문이", result);
	}
	
	/**
	 * update
	 */
	private static void updateOrder(OrderVo vo, List<OrderBookVo> orderBookList) {
		int totalPrice = 0;
		for(OrderBookVo OBVo: orderBookList) {
			totalPrice += OBVo.getPrice() * OBVo.getCount();
		}
		vo.setTotalPrice(totalPrice);
	}
	
	
	/**
	 * 	display
	 * 
	 */
	private static void displayRegister(String object, String msg, Boolean result) {
		System.out.print(object);
		System.out.print(msg);
		System.out.println(result?" 등록되었습니다.": " 등록되지 않았습니다.");
	}
	
	private static <T> void displayInfo(List<T> list) {
		for(T vo : list) {
			System.out.println(vo);
		}
	}
	
	private static void line(String entity) {
		System.out.print("=====================================  ");
		System.out.print(entity);
		System.out.println("  =====================================");
	}

}
