package com.aone.dao;

import java.util.List;

import com.aone.entity.KeyPerson;

public interface KeyPersonDao  extends SuperDao<KeyPerson>{
	
	public KeyPerson searchByIdDao(int id);
	public List<KeyPerson> showPersonByPageDao(String propertyName,boolean desc,Integer startRow,Integer pageSize);

}
