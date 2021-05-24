package com.douzone.bookmall.vo;

public class OrderVo {

	
	private Long id;
	
	private int orderNum;
	private Long memberId;
	private String memberName;
	private String memberEmail;
	private String memberTel;
	private int totalPrice;
	private String address;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberTel() {
		return memberTel;
	}
	public void setMemberTel(String memberTel) {
		this.memberTel = memberTel;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	@Override
	public String toString() {
		return "OrderVo [orderNum=" + orderNum + ", memberName=" + memberName + ", memberEmail=" + memberEmail
				+ ", memberTel=" + memberTel + ", totalPrice=" + totalPrice + ", address=" + address + "]";
	}
	
	
	
}
