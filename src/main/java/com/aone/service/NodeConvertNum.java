package com.aone.service;

import java.util.ArrayList;

import com.aone.entity.Edges;
import com.aone.entity.Nodes;

public interface NodeConvertNum {
	
	public ArrayList<Edges> convertToNum(ArrayList<Nodes> nodesList,ArrayList<Edges> edgeList);
	public StringBuilder JsonDataAndWeightConvert(ArrayList<Nodes> al2,ArrayList<Edges> al1);

}
