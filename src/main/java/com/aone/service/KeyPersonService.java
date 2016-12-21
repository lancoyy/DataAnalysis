package com.aone.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.aone.entity.KeyPerson;
import com.aone.entity.Page;

public interface KeyPersonService {
	public KeyPerson searchById(int id);
	public Page showPersonByPage(int currentpage, String propertyName,boolean desc,Integer startRow,Integer pageSize);
	public ArrayList<HashMap<String, Integer>> keyPersonStatus();
	public ArrayList<HashMap<String,Integer>> commSearchService(int num, String startDate, String endDate, String targetPerson);
}
