package com.aone.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.aone.dao.CommDataLoadDao;
import com.aone.entity.Comm;
import com.aone.entity.Edges;
import com.aone.entity.Nodes;



@Repository(value="CommDataLoadDao")
public class CommDataLoadDaoImpl extends SuperDaoImpl<Comm> implements CommDataLoadDao {

	/**
	 * @author Joe
	 * @function 读取通信数据，将通信关系封装成Edges
	 */
	public ArrayList<Edges> loadAllNodesDao(){
		ArrayList<Edges> edgeList = new ArrayList<Edges>();
		List<Comm> list= this.findAll();
		for(Comm e : list){
			Edges edge= new Edges();
			Nodes source = new Nodes();
			Nodes target = new Nodes();
			source.setGroupId("1");
			source.setName(e.getSource());
			target.setGroupId("1");
			target.setName(e.getTarget());
			edge.setSource(source);
			edge.setTarget(target);
			edgeList.add(edge);
//			System.out.println(e.getSource()+"  "+e.getTarget());
		}
		return edgeList;
		
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
