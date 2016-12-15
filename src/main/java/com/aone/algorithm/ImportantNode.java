package com.aone.algorithm;

import java.util.ArrayList;

import org.gephi.data.attributes.api.AttributeModel;
import org.gephi.graph.api.GraphModel;

import com.aone.entity.Edges;
import com.aone.entity.Nodes;

public interface ImportantNode {
	 public  ArrayList<Nodes> execute(ArrayList<Edges> edgesInfos,GraphModel graphModel, AttributeModel attributeModel);
	 public ArrayList< Nodes> execute(int number,ArrayList<Edges> edgesInfos,GraphModel graphModel, AttributeModel attributeModel);
	 public String getReport();
}
