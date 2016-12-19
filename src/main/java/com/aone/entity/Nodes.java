package com.aone.entity;
/**
 * @author Joe
 * @function 此类封装了通信网络中的节点信息
 * @super 无
 * @type 普通java类
 */
public class Nodes{
	int id;
	String name;
	String groupId;
	String noteWeight;
	String []alias;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String[] getAlias() {
		return alias;
	}
	public void setAlias(String[] alias) {
		this.alias = alias;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getNoteWeight() {
		return noteWeight;
	}
	public void setNoteWeight(String noteWeight) {
		this.noteWeight = noteWeight;
	}
	
	
	
}
