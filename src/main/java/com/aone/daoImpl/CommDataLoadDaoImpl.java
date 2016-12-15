package com.aone.daoImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.aone.dao.CommDataLoadDao;
import com.aone.entity.Telerecord;
import com.aone.entity.Edges;
import com.aone.entity.Nodes;



@Repository(value="CommDataLoadDao")
public class CommDataLoadDaoImpl extends SuperDaoImpl<Telerecord> implements CommDataLoadDao {

	/**
	 * @author Joe
	 * @function 读取通信数据，将通信关系封装成Edges
	 */
	public ArrayList<Edges> loadAllNodesDao(){
		ArrayList<Edges> edgeList = new ArrayList<Edges>();
		List<Telerecord> list = this.findAll();
		Set<String> s = new HashSet<String>();
		for(Telerecord e : list){
			//去重
			if(s.contains(e.getSource()+e.getTarget()) || s.contains(e.getTarget()+e.getSource()))
				continue;
			s.add(e.getSource()+e.getTarget());
			//封装为edge
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
