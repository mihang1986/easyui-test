package com.sao.easyui.test.biz.impl;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.sao.easyui.test.biz.UserBiz;
import com.sao.easyui.test.biz.anno.Business;
import com.sao.easyui.test.dao.entity.User;
import com.sao.easyui.test.service.UserService;

@Business
public class UserBizImpl implements UserBiz {

	@Resource
	private UserService userService;
	
	@Override
	@Transactional
	public void addTwoUser(User user1, User user2) {
		userService.insertUser(user1);
		userService.insertUser(user2);
	}

}
