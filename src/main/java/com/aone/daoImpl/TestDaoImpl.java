package com.aone.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.aone.dao.TestDao;
import com.aone.entity.Test;

@Repository(value="testDao")
public class TestDaoImpl extends SuperDaoImpl<Test> implements TestDao {

	@Override
	public <T> List<T> findByCriteria(final T object, final Integer startRow,
			final Integer pageSize) {
		return null;
	}

	@Override
	public <T> Long findByCriteriaCount(T object) {
		// TODO Auto-generated method stub
		return null;
	}

}
