package com.sao.easyui.test.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sao.easyui.test.controller.MainController;
import com.sao.easyui.test.dao.SysDictionaryMapper;
import com.sao.easyui.test.dao.entity.SysDictionary;
import com.sao.easyui.test.service.DictionaryService;

@Service
@Scope(value = "singleton")
public class DictionaryServiceImpl implements DictionaryService {
	private final Logger logger = LoggerFactory.getLogger(DictionaryServiceImpl.class);
	
	private Map<String, Map<String, String>> dictionary;
	
	@Resource
	private SysDictionaryMapper sysDictionaryMapper;
	
	@PostConstruct
	public void init(){
		logger.info("正在初始化字典数据....");
		dictionary = new HashMap<String, Map<String, String>>();
		List<SysDictionary> dics = sysDictionaryMapper.selectAll();
		
		for(SysDictionary dic : dics){
			if(!dictionary.containsKey(dic.getDicType())){
				dictionary.put(dic.getDicType(), new HashMap<String, String>());
			}
			
			dictionary.get(dic.getDicType()).put(dic.getDicValue(), dic.getDicTrans());
		}
	}
	
	@Override
	public String translate(String type, String value) {
		return !dictionary.containsKey(type) ? "" : 
			!dictionary.get(type).containsKey(value) ? "" : 
				dictionary.get(type).get(value);
	}

}
