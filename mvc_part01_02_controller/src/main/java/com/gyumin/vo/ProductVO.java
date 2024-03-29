package com.gyumin.vo;

/*
 * 상품 정보를 저장하는 객체
 */
public class ProductVO {
	
	private String name;
	private int price;
	
	public ProductVO() {
		System.out.println("기본 생성자 호출");
	}
	
	public ProductVO(String name, int price) {
		super();
		this.name = name;
		this.price = price;
		System.out.println("매개변수 두개르 전달 받는 생성자 호출");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println("setName 호출 - " + name);
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		System.out.println("setPrice 호출 - " + price);
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductVO [name=" + name + ", price=" + price + "]";
	}
}

