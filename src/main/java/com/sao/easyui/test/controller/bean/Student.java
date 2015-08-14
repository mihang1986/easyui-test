package com.sao.easyui.test.controller.bean;

import java.util.List;

import com.sao.easyui.test.controller.anno.TranslatePart;

public class Student {
	private String id;
	private String name;
	
	@TranslatePart(type = "gender", field="genderCCC")
	private String gender;
	
	@TranslatePart(type = "yesno", field="fdsafadsfads")
	private String is18r;
	

	private List<Address> addreses;
	
	
	public List<Address> getAddreses() {
		return addreses;
	}
	public void setAddreses(List<Address> addreses) {
		this.addreses = addreses;
	}
	public Student() {
		super();
	}
	public Student(String id, String name, String gender, String is18r) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.is18r = is18r;
	}
	
	

	public String getIs18r() {
		return is18r;
	}
	public void setIs18r(String is18r) {
		this.is18r = is18r;
	}
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
