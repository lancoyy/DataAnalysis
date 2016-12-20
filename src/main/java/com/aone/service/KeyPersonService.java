package com.aone.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.aone.entity.KeyPerson;

public interface KeyPersonService {
	public KeyPerson searchById(int id);
	public List<KeyPerson> showPersonByPage(String propertyName,boolean desc,Integer startRow,Integer pageSize);
	public ArrayList<HashMap<String, Integer>> keyPersonStatus();
}
