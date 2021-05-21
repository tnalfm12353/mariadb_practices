package com.douzone.bookmall.test;

import java.util.List;

import com.douzone.bookmall.dao.BookDao;
import com.douzone.bookmall.vo.BookVo;

public class BookDaoTest {

	public static void main(String[] args) {
		insert();
		findAll();
		new BookDao().delete();
	}

	private static void insert() {
		BookVo vo = null;
		vo = new BookVo();
		vo.setCategoryId(1l);
		vo.setTitle("이것이 자바다");
		vo.setPrice(50000);
		
		new BookDao().insert(vo);
		
	}
	private static void findAll() {
		List<BookVo> list = new BookDao().findAll();
		for(BookVo vo : list) {
			System.out.println(vo);
		}
	}
}
