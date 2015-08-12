package com.sao.test;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sao.easyui.test.dao.SysDictionaryMapper;
import com.sao.easyui.test.dao.entity.SysDictionary;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml", "classpath:mybatis-context.xml"})
public class TestCase01 {
	
	@Resource
	private SysDictionaryMapper sysDictionaryMapper;
	
	@Test
	public void test1(){
		sysDictionaryMapper.insert(new SysDictionary("a", "b", "c", "d", new Date(), "ff"));
	}
}
