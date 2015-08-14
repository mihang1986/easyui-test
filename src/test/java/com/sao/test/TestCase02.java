package com.sao.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.sao.easyui.test.controller.bean.Address;
import com.sao.easyui.test.controller.bean.Student;

public class TestCase02 {
	@Before
	public void startup(){

	}
	
	@Test
	public void test01() throws IllegalArgumentException, IllegalAccessException, InstantiationException{
//		Student s = new Student();
//		s.setId("a");
//		s.setName("b");
//		s.setGender("c");
//		s.setIs18r("1");
//		s = eachObject(s, callback);
	}
	
	@Test
	public void test02() throws IllegalArgumentException, IllegalAccessException, InstantiationException{
//		List<Student> students = new ArrayList<Student>();
//		students.add(new Student("1", "mihang", "m", "1"));
//		students.add(new Student("2", "laowang", "f", "1"));
//		students = eachObject(students, callback);
	}
	
	@Test
	public void test03() throws IllegalArgumentException, IllegalAccessException, InstantiationException{
		List<Address> addrs1 = new ArrayList<Address>();
		addrs1.add(new Address("1", "2"));
		addrs1.add(new Address("2", "3"));
		
		List<Address> addrs2 = new ArrayList<Address>();
		addrs2.add(new Address("3", "2"));
		addrs2.add(new Address("4", "3"));
		
		List<Student> students = new ArrayList<Student>();
		Student stu1 = new Student("1", "mihang", "m", "1");
		Student stu2 = new Student("2", "laowang", "f", "1");
		
		
		stu1.setAddreses(addrs1);
		stu2.setAddreses(addrs2);
		
		students.add(stu1);
		students.add(stu2);
		

		
//		students = eachObject(students, callback);
	}
}
