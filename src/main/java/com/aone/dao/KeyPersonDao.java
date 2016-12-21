package com.aone.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.aone.entity.KeyPerson;

public interface KeyPersonDao  extends SuperDao<KeyPerson>{
	
	public KeyPerson searchByIdDao(int id);
	public List<KeyPerson> showPersonByPageDao(String propertyName,boolean desc,Integer startRow,Integer pageSize);
	public List countByProperty(String property);
	public ArrayList<HashMap<String, Integer>> keyPersonStatusDao();
	public Long getPageTotal();
	public ArrayList<HashMap<String,Integer>> commSearchDao(int num, String startDate, String endDate, String targetPerson);
}
