package com.aone.dao;


import java.util.ArrayList;

import com.aone.entity.Telerecord;
import com.aone.entity.Edges;

public interface CommDataLoadDao extends SuperDao<Telerecord> {
	
	public ArrayList<Edges> loadAllNodesDao();
}
