package com.aone.daoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aone.dao.KeyPersonDao;
import com.aone.entity.KeyPerson;;



@Repository(value="keyPersonDao")
public class KeyPersonDaoImpl extends SuperDaoImpl<KeyPerson> implements KeyPersonDao{
	
	
	/**
	 * @author Joe
	 * @function 根据id查询重点人物信息
	 */
	public KeyPerson searchByIdDao(int id){
		return this.findById(id);
	}
	
	/**
	 * @author Joe
	 * @function 按照propertyName分页查询重点人员
	 */
	public List<KeyPerson> showPersonByPageDao(String propertyName,boolean desc,Integer startRow,Integer pageSize){
		return this.findAllByPage(propertyName, desc, startRow, pageSize);
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
