package com.douzone.bookmall.test;

import java.util.List;

import com.douzone.bookmall.dao.MemberDao;
import com.douzone.bookmall.vo.MemberVo;

public class MemberDaoTest {

	public static void main(String[] args) {
		insert();
		findAll();
		delete();

	}

	private static void delete() {
		new MemberDao().delete();
		
	}

	private static void findAll() {
		List<MemberVo> list = new MemberDao().findAll();
		for(MemberVo vo : list) {
			System.out.println(vo);
		}
	}

	private static void insert() {
		MemberVo vo = new MemberVo();
		vo.setName("김재홍");
		vo.setTel("010-2222-2222");
		vo.setEmail("asdasd@asdasd.com");
		vo.setPassword("asdasdasd");
		new MemberDao().insert(vo);
	}

}
