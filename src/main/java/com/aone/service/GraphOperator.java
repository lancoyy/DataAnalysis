package com.aone.service;

import java.util.ArrayList;

import com.aone.entity.AllNodesandEdges;
import com.aone.entity.Edges;

public interface GraphOperator {
	public AllNodesandEdges GraphOperator2(int num,ArrayList<Edges> edgesInfos);
	public void keynodeUpdate(int min, int max, String columnId);
}
