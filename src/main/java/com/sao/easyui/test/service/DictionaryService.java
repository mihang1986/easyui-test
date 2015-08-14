package com.sao.easyui.test.service;

import java.util.List;

import com.sao.easyui.test.dao.entity.SysDictionary;

public interface DictionaryService {
	public String translate(String type, String value);
	
	public List<SysDictionary> getDicsByType(String... types);
}
