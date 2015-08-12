package com.sao.easyui.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sao.easyui.test.controller.anno.NeedTranslate;
import com.sao.easyui.test.controller.bean.Student;
import com.sao.easyui.test.controller.interceptor.CommonInterceptor;

@Controller
public class MainController {
	private final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(){
		return "index";
	}
	
	@ResponseBody
	@NeedTranslate
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public Student index(HttpServletRequest request){
		Student result = new Student();
		result.setId("1");
		result.setName("mihang");
		result.setGender("1");
		return result;
	}
}
