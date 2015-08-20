package com.sao.easyui.test.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sao.easyui.test.dao.entity.User;
import com.sao.easyui.test.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Resource
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<User> allUser(){
		return userService.findAllUser();
	}
}
