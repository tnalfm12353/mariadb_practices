package com.douzone.bookmall.test;

import java.util.List;

import com.douzone.bookmall.dao.CategoryDao;
import com.douzone.bookmall.vo.CategoryVo;

public class CategoryDaoTest {

	public static void main(String[] args) {
		insertCategory();
		findAllCategories();
		new CategoryDao().delete();
	}
	
	private static void findAllCategories() {
		List<CategoryVo> list= new CategoryDao().findAll();
		for(CategoryVo vo : list) {
			System.out.println(vo);
		}
	}

	public static void insertCategory() {
		CategoryVo vo = null;
		
		vo = new CategoryVo();
		vo.setCategory("인문");
		new CategoryDao().insert(vo);
		
		vo = new CategoryVo();
		vo.setCategory("IT");
		new CategoryDao().insert(vo);
		
		vo = new CategoryVo();
		vo.setCategory("소설");
		new CategoryDao().insert(vo);
	}

}
