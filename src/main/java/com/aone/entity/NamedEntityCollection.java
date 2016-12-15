package com.aone.entity;

import java.util.ArrayList;
import java.util.List;

public class NamedEntityCollection {
	private List<NameEntity> nameEntities;
	private List<NameEntity> placeEntities;
	private List<NameEntity> orginalEntities;
	private List<NameEntity> otherEntities;
	private List<TimeEntity> timeEntities;
	
	public NamedEntityCollection(){
		this.nameEntities = new ArrayList<NameEntity>();
		this.placeEntities = new ArrayList<NameEntity>();
		this.orginalEntities = new ArrayList<NameEntity>();
		this.otherEntities = new ArrayList<NameEntity>();
		this.timeEntities = new ArrayList<TimeEntity>();
	}
	
	public NamedEntityCollection(List<NameEntity> nameEntities, List<NameEntity> placeEntities, List<NameEntity> orginalEntities, List<NameEntity> otherEntities, List<TimeEntity> timeEntities){
		this.nameEntities = nameEntities;
		this.placeEntities = placeEntities;
		this.orginalEntities = orginalEntities;
		this.otherEntities = otherEntities;
		this.timeEntities = timeEntities;
	}
	
	public List<NameEntity> getNameEntities() {
		return nameEntities;
	}

	public void setNameEntities(List<NameEntity> nameEntities) {
		this.nameEntities = nameEntities;
	}

	public List<NameEntity> getPlaceEntities() {
		return placeEntities;
	}

	public void setPlaceEntities(List<NameEntity> placeEntities) {
		this.placeEntities = placeEntities;
	}

	public List<NameEntity> getOrginalEntities() {
		return orginalEntities;
	}

	public void setOrginalEntities(List<NameEntity> orginalEntities) {
		this.orginalEntities = orginalEntities;
	}

	public List<NameEntity> getOtherEntities() {
		return otherEntities;
	}

	public void setOtherEntities(List<NameEntity> otherEntities) {
		this.otherEntities = otherEntities;
	}

	public List<TimeEntity> getTimeEntities() {
		return timeEntities;
	}

	public void setTimeEntities(List<TimeEntity> timeEntities) {
		this.timeEntities = timeEntities;
	}

}
