package com.sao.easyui.test.dao.entity;

import java.util.Date;

public class SysDictionary {
    private String dicType;

    private String dicValue;

    private String dicTrans;

    private String dicDesc;

    private Date modifyTime;

    private String modifyBy;

    public SysDictionary() {
		super();
	}

	public SysDictionary(String dicType, String dicValue, String dicTrans,
			String dicDesc, Date modifyTime, String modifyBy) {
		super();
		this.dicType = dicType;
		this.dicValue = dicValue;
		this.dicTrans = dicTrans;
		this.dicDesc = dicDesc;
		this.modifyTime = modifyTime;
		this.modifyBy = modifyBy;
	}

	public String getDicType() {
        return dicType;
    }

    public void setDicType(String dicType) {
        this.dicType = dicType;
    }

    public String getDicValue() {
        return dicValue;
    }

    public void setDicValue(String dicValue) {
        this.dicValue = dicValue;
    }

    public String getDicTrans() {
        return dicTrans;
    }

    public void setDicTrans(String dicTrans) {
        this.dicTrans = dicTrans;
    }

    public String getDicDesc() {
        return dicDesc;
    }

    public void setDicDesc(String dicDesc) {
        this.dicDesc = dicDesc;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }
}