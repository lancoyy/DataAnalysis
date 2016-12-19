package com.aone.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.aone.dao.ContradictionDao;
import com.aone.entity.Contradiction;

@Repository(value="contradictionDao")
public class ContradictionDaoImpl extends SuperDaoImpl<Contradiction> implements ContradictionDao {
	
	//返回所有事件记录
	public List<Contradiction> findAllDao(){
		return this.findAll();
	}
	
	//返回指定重点人员所涉及的矛盾事件
	public List<Contradiction> findContradictionsDao(String target){
		Object[] param=new Object[]{target};
		List<Contradiction>  list = this.findBySql("SELECT * FROM Contradiction WHERE peopleInfo LIKE ?",param);
		//测试
//		for(Contradiction c:list)
//			System.out.println(c.getId());
		return list;
	}

	@Override
	public <T> List<T> findByCriteria(T object, Integer startRow, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Long findByCriteriaCount(T object) {
		// TODO Auto-generated method stub
		return null;
	}

}
