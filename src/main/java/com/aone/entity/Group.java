package com.aone.entity;

import java.util.ArrayList;

/**
 * @author Joe
 * @function 此类封装了社团内点和边的集合
 * @super 无
 */
public class Group {
	private String groupId;//集合ID
	private ArrayList<Nodes> noteList;//节点集合
	private ArrayList<Edges> edgeList;//边集合
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public ArrayList<Nodes> getNoteList() {
		return noteList;
	}
	public void setNoteList(ArrayList<Nodes> noteList) {
		this.noteList = noteList;
	}
	public ArrayList<Edges> getEdgeList() {
		return edgeList;
	}
	public void setEdgeList(ArrayList<Edges> edgeList) {
		this.edgeList = edgeList;
	}
	

	
}
