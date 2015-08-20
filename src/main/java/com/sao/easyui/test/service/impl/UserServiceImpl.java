package com.sao.easyui.test.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sao.easyui.test.dao.UserMapper;
import com.sao.easyui.test.dao.entity.User;
import com.sao.easyui.test.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserMapper userMapper;
	
	
	public UserServiceImpl(){
		System.out.println("fdsafads");
	}
	
	
	@Override
	@Transactional
	public void insertUser(User user){
		userMapper.insert(user);
	}

	@Override
	public List<User> findAllUser() {
		return userMapper.findAllUser();
	}
}
