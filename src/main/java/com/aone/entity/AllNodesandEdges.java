package com.aone.entity;

import java.util.ArrayList;

/**
 * @author Joe
 * @function 此类封装了所有点，边，组和连接不同社团线的集合
 * @super 无
 */
public class AllNodesandEdges {
	//所有点的集合
	private ArrayList<Nodes> nodeList;
	//所有边的集合
	private ArrayList<Edges> EdgeList;
	//所有社团的集合
	private ArrayList<Group> allGroups;
	//所有连接不同社团线的集合
	private ArrayList<Edges> outEdgeList;
	public ArrayList<Nodes> getNodeList() {
		return nodeList;
	}
	public void setNodeList(ArrayList<Nodes> nodeList) {
		this.nodeList = nodeList;
	}
	public ArrayList<Edges> getEdgeList() {
		return EdgeList;
	}
	public void setEdgeList(ArrayList<Edges> edgeList) {
		EdgeList = edgeList;
	}
	public ArrayList<Group> getAllGroups() {
		return allGroups;
	}
	public void setAllGroups(ArrayList<Group> allGroups) {
		this.allGroups = allGroups;
	}
	public ArrayList<Edges> getOutEdgeList() {
		return outEdgeList;
	}
	public void setOutEdgeList(ArrayList<Edges> outEdgeList) {
		this.outEdgeList = outEdgeList;
	}
	
	
}
