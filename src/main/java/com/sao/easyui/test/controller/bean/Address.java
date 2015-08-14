package com.sao.easyui.test.controller.bean;

import com.sao.easyui.test.controller.anno.TranslatePart;

public class Address {
	@TranslatePart(type="country")
	private String country;
	
	@TranslatePart(type="province")
	private String province;
	
	
	public Address() {
		super();
	}
	public Address(String country, String province) {
		super();
		this.country = country;
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
	
}
