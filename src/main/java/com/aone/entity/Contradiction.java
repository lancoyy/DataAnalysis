package com.aone.entity;
 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contradiction")
public class Contradiction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="code", nullable = true, length = 20)
	private String code;
	
	@Column(name="name", nullable = true, length = 50)
	private String name;
	
	@Column(name="territory", nullable = true, length = 100)
	private String territory;
	
	@Column(name="keyProblem", nullable = true, length = 255)
	private String keyProblem;
	
	@Column(name="peopleCount", nullable = true, length = 11)
	private Integer peopleCount;
	
	@Column(name="peopleInfo", nullable = true, length = 255)
	private String peopleInfo;
	
	@Column(name="keyword", nullable = true, length = 100)
	private String keyword;
	
	@Column(name="issueProperty", nullable = true, length = 100)
	private String issueProperty;
	
	@Column(name="involvedGroup", nullable = true, length = 50)
	private String involvedGroup;
	
	@Column(name="issueContent", nullable = true)
	private String issueContent;
	
	@Column(name="attachment", nullable = true, length = 200)
	private String attachment;
	
	@Column(name="directorDepartment", nullable = true, length = 50)
	private String directorDepartment;
	
	@Column(name="solveRecord", nullable = true, length = 200)
	private String solveRecord;
	
	@Column(name="isDelete", nullable = true, length = 11)
	private Integer isDelete;
	//需调整类型
	@Column(name="addTime", nullable = true)
	private String addTime;
	
	@Column(name="addBy", nullable = true, length = 255)
	private String addBy;
	//需调整类型
	@Column(name="lastUpdateTime", nullable = true)
	private String lastUpdateTime;
	
	@Column(name="lastUpdateBy", nullable = true, length = 255)
	private String lastUpdateBy;
	
	@Column(name="intelligence", nullable = true)
	private String intelligence;
	
	@Column(name="dynamics", nullable = true)
	private String dynamics;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTerritory() {
		return territory;
	}

	public void setTerritory(String territory) {
		this.territory = territory;
	}

	public String getKeyProblem() {
		return keyProblem;
	}

	public void setKeyProblem(String keyProblem) {
		this.keyProblem = keyProblem;
	}

	public Integer getPeopleCount() {
		return peopleCount;
	}

	public void setPeopleCount(Integer peopleCount) {
		this.peopleCount = peopleCount;
	}

	public String getPeopleInfo() {
		return peopleInfo;
	}

	public void setPeopleInfo(String peopleInfo) {
		this.peopleInfo = peopleInfo;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getIssueProperty() {
		return issueProperty;
	}

	public void setIssueProperty(String issueProperty) {
		this.issueProperty = issueProperty;
	}

	public String getInvolvedGroup() {
		return involvedGroup;
	}

	public void setInvolvedGroup(String involvedGroup) {
		this.involvedGroup = involvedGroup;
	}

	public String getIssueContent() {
		return issueContent;
	}

	public void setIssueContent(String issueContent) {
		this.issueContent = issueContent;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getDirectorDepartment() {
		return directorDepartment;
	}

	public void setDirectorDepartment(String directorDepartment) {
		this.directorDepartment = directorDepartment;
	}

	public String getSolveRecord() {
		return solveRecord;
	}

	public void setSolveRecord(String solveRecord) {
		this.solveRecord = solveRecord;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getAddBy() {
		return addBy;
	}

	public void setAddBy(String addBy) {
		this.addBy = addBy;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public String getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(String intelligence) {
		this.intelligence = intelligence;
	}

	public String getDynamics() {
		return dynamics;
	}

	public void setDynamics(String dynamics) {
		this.dynamics = dynamics;
	}
	
	

}
