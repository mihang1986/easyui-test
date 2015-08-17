package com.sao.easyui.test.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sao.easyui.test.biz.UserBiz;
import com.sao.easyui.test.controller.anno.TranslateNeed;
import com.sao.easyui.test.controller.bean.ResultBean;
import com.sao.easyui.test.dao.entity.User;
import com.sao.easyui.test.pub.Constants;
import com.sao.easyui.test.service.UserService;

@Controller
public class MainController {
	private final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Resource
	private UserService userService;
	
	@Resource
	private UserBiz userBiz;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(){
		return "index";
	}
	
	@ResponseBody
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public Object addUser(HttpServletRequest request){
		User user1 = new User();
		user1.setId("1");
		user1.setName("shabi1");
		user1.setDob(new Date());
		
		User user2 = new User();
		user2.setId("2");
		user2.setName("shabi2");
		user2.setDob(new Date());
		
		userBiz.addTwoUser(user1, user2);
		
		
		return new ResultBean(Constants.RESULT_SUCCESS, Constants.RESULT_SUCCESS_MESSAGE, null);
	}
	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error(){
		throw new RuntimeException("error");
	}
	
	@ResponseBody
	@TranslateNeed
	@RequestMapping(value = "/jsonError", method = RequestMethod.GET)
	public Object jsonError(){
		throw new RuntimeException("error");
		//return new Student("1", "2", "3", "4");
	}
}
