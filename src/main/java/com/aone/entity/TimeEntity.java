package com.aone.entity;

import java.util.Date;

public class TimeEntity {
	private String originalTime;
	private String normalTime;
	private Date time;
	
	public TimeEntity(){}
	
	public TimeEntity(String originalTime, String normalTime, Date time){
		this.originalTime = originalTime;
		this.normalTime = normalTime;
		this.time = time;
	}

	public String getOriginalTime() {
		return originalTime;
	}

	public void setOriginalTime(String originalTime) {
		this.originalTime = originalTime;
	}

	public String getNormalTime() {
		return normalTime;
	}

	public void setNormalTime(String normalTime) {
		this.normalTime = normalTime;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
