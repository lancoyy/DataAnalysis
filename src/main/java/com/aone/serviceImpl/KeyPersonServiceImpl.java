package com.aone.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aone.dao.ContradictionDao;
import com.aone.dao.KeyPersonDao;
import com.aone.entity.Contradiction;
import com.aone.entity.KeyPerson;
import com.aone.entity.Page;
import com.aone.service.KeyPersonService;

@Service(value="keyPersonService")
@Transactional
public class KeyPersonServiceImpl implements KeyPersonService{
	
	@Resource
	@Qualifier(value="keyPersonDao")
	private KeyPersonDao keyPersonDao;
	
	@Resource
	@Qualifier(value="contradictionDao")
	private ContradictionDao contradictionDao;
	
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
	 * @param currentpage 当前页
	 * @param propertyName 排序属性名称
	 * @param desc 是否倒序
	 * @param startRow 起始记录
	 * @param pageSize 分页大小
	 * @return
	 */
	public Page showPersonByPage(int currentpage, String propertyName,boolean desc,Integer startRow,Integer pageSize){
		List<KeyPerson> kpList = keyPersonDao
				.showPersonByPageDao(propertyName, desc, startRow, pageSize);//获得当前显示人员
		// 测试
		// for(KeyPerson kp : kpList){
		// System.out.println(kp.getName()+"---"+kp.getFraction());
		// }
		int pageTotal = (int) Math.ceil(keyPersonDao.getPageTotal()/pageSize);//计算总页数
		Page page = new Page(currentpage, pageTotal, kpList);
		return  page;
	}

	/**
	 * @author Joe
	 * @function 对keyperson表中的问题属地,问题类别，各级别的重点人数进行统计
	 * @return
	 */
	public ArrayList<HashMap<String, Integer>> keyPersonStatus(){
		return keyPersonDao.keyPersonStatusDao();
	}
	
	/**
	 * @author Joe
	 * @function 条件检索：获得指定时间范围内，通信次数大于num的统计信息
	 */
	public ArrayList<HashMap<String,Integer>> commSearchService(int num, String startDate, String endDate, String targetPerson){
		return keyPersonDao.commSearchDao(num, startDate, endDate, targetPerson);
	}
}
