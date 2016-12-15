package com.aone.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aone.dao.TestDao;
import com.aone.entity.Test;
import com.aone.service.TestService;

@Service(value="testService")
@Transactional
public class TestServiceImpl implements TestService {
	
	@Resource
	@Qualifier(value="testDao")
	private TestDao testDao;
	
	@Override
	public Test getAnById(int id) {
		Test test = testDao.findById(id);
		System.out.println(test.getId() + ":  " + test.getName());
		return test;
	}

	@Override
	public void addAn() {

	}

	@Override
	public void delAn() {

	}

	@Override
	public List<Test> findAll() {
		
		return testDao.findAll();
	}

}
