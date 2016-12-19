package com.aone.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;

import com.aone.dao.ContradictionDao;
import com.aone.entity.Contradiction;

public class ContradictionServiceImpl {

	@Resource
	@Qualifier(value="contradictionDao")
	private ContradictionDao contradictionDao;

	//返回所有事件记录
	public void findAllContradictions(){
		List<Contradiction> list = contradictionDao.findAllDao();
	}
	
	
	/**
	 * @author Joe
	 * @function 返回该人员所涉及的矛盾事件
	 * @param id
	 * @return 
	 */
	public List<Contradiction> findContradictionsByPerson(int id){
		String target = "%{"+String.valueOf(id)+":%";
		return contradictionDao.findContradictionsDao(target);
	}
}
