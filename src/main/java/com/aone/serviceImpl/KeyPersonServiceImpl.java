package com.aone.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aone.dao.KeyPersonDao;
import com.aone.entity.KeyPerson;
import com.aone.service.KeyPersonService;

@Service(value="keyPersonService")
@Transactional
public class KeyPersonServiceImpl implements KeyPersonService{
	
	@Resource
	@Qualifier(value="keyPersonDao")
	private KeyPersonDao keyPersonDao;
	
	/**
	 * @author Joe
	 * @function 根据id查询重点人物信息
	 * @param id
	 */
	public KeyPerson searchById(int id){
		return keyPersonDao.searchByIdDao(id);
	}

	
	/**
	 * @author Joe
	 * @function 按属性名分页查询重点人员
	 * @param propertyName 排序属性名称
	 * @param desc 是否倒序
	 * @param startRow 起始记录
	 * @param pageSize 分页大小
	 * @return
	 */
	public List<KeyPerson> showPersonByPage(String propertyName,boolean desc,Integer startRow,Integer pageSize){
		return keyPersonDao.showPersonByPageDao(propertyName, desc, startRow, pageSize);
	}
	
	

}
