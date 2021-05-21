package com.douzone.bookmall.test;

import java.util.List;

import com.douzone.bookmall.dao.CartDao;
import com.douzone.bookmall.vo.CartVo;

public class CartDaoTest {

	public static void main(String[] args) {
//		insert();
		findAll();
		new CartDao().delete();

	}

	private static void findAll() {
		List<CartVo> list = new CartDao().findAll(); 
		for(CartVo vo : list) {
			System.out.println(vo);
		}
	}

	private static void insert() {
		CartVo vo = new CartVo();
		vo.setBookId(1L);
		vo.setMemberId(1L);
		vo.setCount(2);
		
		new CartDao().insert(vo);
	}

}
