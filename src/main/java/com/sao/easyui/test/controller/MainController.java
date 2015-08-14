package com.sao.easyui.test.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sao.easyui.test.controller.anno.TranslateNeed;
import com.sao.easyui.test.controller.bean.Address;
import com.sao.easyui.test.controller.bean.Student;

@Controller
public class MainController {
	private final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(){
		return "index";
	}
	
	@ResponseBody
	@TranslateNeed
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public List<Student> index(HttpServletRequest request){
		List<Address> addrs1 = new ArrayList<Address>();
		addrs1.add(new Address("1", "2"));
		addrs1.add(new Address("2", "1"));
		
		List<Address> addrs2 = new ArrayList<Address>();
		addrs2.add(new Address("1", "2"));
		addrs2.add(new Address("2", "1"));
		
		List<Student> students = new ArrayList<Student>();
		Student stu1 = new Student("1", "mihang", "m", "1");
		Student stu2 = new Student("2", "laowang", "f", "1");
		
		
		stu1.setAddreses(addrs1);
		stu2.setAddreses(addrs2);
		
		students.add(stu1);
		students.add(stu2);
		
		return students;
	}
}
