package com.aone.entity;

import javax.persistence.Entity;

/**
 * @author Joe
 * @function 此类用来封装通信数据中的source和target类，
 * @super 无
 * @type 普通java类
 */
public class Edges {
	private Nodes source;
	private Nodes target;
	private String weight;
	
	public Nodes getSource() {
		return source;
	}
	public void setSource(Nodes source) {
		this.source = source;
	}
	public Nodes getTarget() {
		return target;
	}
	public void setTarget(Nodes target) {
		this.target = target;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	
}
