package com.douzone.bookmall.test;

import java.util.List;

import com.douzone.bookmall.dao.OrderDao;
import com.douzone.bookmall.vo.OrderVo;

public class OrderDaoTest {

	public static void main(String[] args) {
//		insert();
		findAll();
		new OrderDao().delete();

	}

	private static void findAll() {
		List<OrderVo> list = new OrderDao().findAll();
		
		
	}

	private static void insert() {
		
		
	}

}
