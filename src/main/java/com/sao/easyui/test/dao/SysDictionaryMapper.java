package com.sao.easyui.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sao.easyui.test.dao.entity.SysDictionary;

public interface SysDictionaryMapper {
    int deleteByPrimaryKey(@Param("dicType") String dicType, @Param("dicValue") String dicValue);

    int insert(SysDictionary record);

    int insertSelective(SysDictionary record);

    SysDictionary selectByPrimaryKey(@Param("dicType") String dicType, @Param("dicValue") String dicValue);

    int updateByPrimaryKeySelective(SysDictionary record);

    int updateByPrimaryKey(SysDictionary record);
    
    List<SysDictionary> selectAll();
    
    List<SysDictionary> selectByTypes(@Param("types") String... types);
}