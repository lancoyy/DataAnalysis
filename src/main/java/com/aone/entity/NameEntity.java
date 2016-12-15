package com.aone.entity;

public class NameEntity {
	private String name;
	private String nature;
	private Float score;
	private Integer times;
	
	public NameEntity(){}
	
	public NameEntity(String name, String nature){
		this.name = name;
		this.nature = nature;
	}
	
	public NameEntity(String name, String nature, Float score, Integer times){
		this.name = name;
		this.nature = nature;
		this.score = score;
		this.times = times;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

}
