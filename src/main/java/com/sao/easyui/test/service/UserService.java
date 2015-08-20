package com.sao.easyui.test.service;

import java.util.List;

import com.sao.easyui.test.dao.entity.User;

public interface UserService {

	void insertUser(User user);
	
	List<User> findAllUser();

}