package com.aone.service;

import java.util.List;

import com.aone.entity.Test;

public interface TestService {
	public Test getAnById(int id);
	public void addAn();
	public void delAn();
	public List<Test> findAll();
}
