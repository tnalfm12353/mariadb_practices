package com.douzone.bookmall.vo;

public class CategoryVo {

	private Long id;
	
	private String category;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CategoryVo [id=" + id + ", category=" + category + "]";
	}
	
	
}
