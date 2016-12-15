package com.aone.dao;


import java.util.ArrayList;

import com.aone.entity.Comm;
import com.aone.entity.Edges;

public interface CommDataLoadDao extends SuperDao<Comm> {
	
	public ArrayList<Edges> loadAllNodesDao();
}
