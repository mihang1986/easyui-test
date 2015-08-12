package com.sao.easyui.test.controller.bean;

import com.sao.easyui.test.controller.anno.NeedTranslate;

public class Student {
	private String id;
	private String name;
	@NeedTranslate(type = "gender", field="genderTran")
	private String gender;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
