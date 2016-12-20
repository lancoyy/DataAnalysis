package com.aone.daoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
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

		List list = new ArrayList<Map<String, Double>>();
		Long num = this.findAllCount();
		
		List l1 = this.countByProperty("territory");
		for(int i=0;i<l1.size();i++)
			System.out.println((String)((Map)l1.get(i)).
					get("territory")+"--"+Double.parseDouble(((Map)l1.get(i)).get("count(*)").toString())/num);
			
		return this.findAllByPage(propertyName, desc, startRow, pageSize);
	}
	
	/**
	 * @author Joe
	 * @function 统计property属性不同取值的记录数
	 * @param property
	 * @return List<Map<String,Object>>
	 */
	public List countByProperty(String property){
		String sql = "SELECT "+property+", count(*) from keyperson group by "+property;
		Query query = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
	
	
	/**
	 * @author Joe
	 * @function 统计keyperson表中，territory属性，issueContent属性，level属性不同取值的记录数
	 * @return ArrayList<HashMap<String, Integer>>
	 */
	public ArrayList<HashMap<String, Integer>> keyPersonStatusDao(){
		ArrayList<HashMap<String, Integer>> list = new ArrayList<HashMap<String, Integer>>();
//		Long num = this.findAllCount();
		
		//重点人员问题属地统计
		List l1 = this.countByProperty("territory");
		HashMap<String, Integer> map1 = new HashMap<String, Integer>();
		for(int i=0;i<l1.size();i++)
			map1. put((String)((Map)l1.get(i)).get("territory")
					, Integer.parseInt(((Map)l1.get(i)).get("count(*)").toString()));
		//重点人员问题类别统计
		List l2 = this.countByProperty("issueContent");
		HashMap<String, Integer> map2 = new HashMap<String, Integer>();
		for(int i=0;i<l2.size();i++)
			map2. put((String)((Map)l2.get(i)).get("issueContent")
					, Integer.parseInt(((Map)l2.get(i)).get("count(*)").toString()));
		//重点人员各级别的人数统计
		List l3 = this.countByProperty("level");
		HashMap<String, Integer> map3 = new HashMap<String, Integer>();
		for(int i=0;i<l3.size();i++)
			map3. put((String)((Map)l3.get(i)).get("level")
					, Integer.parseInt(((Map)l3.get(i)).get("count(*)").toString()));

		list.add(map1);
		list.add(map2);
		list.add(map3);
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
