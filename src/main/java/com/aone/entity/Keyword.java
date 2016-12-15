package com.aone.entity;

public class Keyword {
	
	private String word;
	private String nature;
	private Float score;
	private Integer times;
	
	public Keyword(){}
	
	public Keyword(String word){
		this.word = word;
	}
	
	public Keyword(String word, String nature, Float score, Integer times){
		this.word = word;
		this.nature = nature;
		this.score = score;
		this.times = times;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
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
