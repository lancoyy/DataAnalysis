package com.aone.serviceImpl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aone.dao.CommDataLoadDao;
import com.aone.entity.Edges;
import com.aone.service.CommDataLoad;

@Service(value="commDataLoad")
@Transactional
public class CommDataLoadImpl implements CommDataLoad{
	
	@Resource
	@Qualifier(value="commDataLoadDao")
	private CommDataLoadDao commDataLoadDao;
	
	/**
	 * @author Joe
	 * @function 将通信数据封装成ArrayList<Edges>
	 */
	public ArrayList<Edges> loadAllNodes() {

		return commDataLoadDao.loadAllNodesDao();
	}


}
