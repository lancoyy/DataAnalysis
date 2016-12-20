package com.aone.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "keyperson")
public class KeyPerson {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int keyPersonID;

	@Column(name = "keypersonname", nullable = true, length = 50)
	private String keypersonname;

	@Column(name = "category", nullable = true)
	private String category;

	@Column(name = "level", nullable = true)
	private String level;

	@Column(name = "popedom", nullable = true)
	private String popedom;

	@Column(name = "jobName", nullable = true)
	private String jobName;

	@Column(name = "leader", nullable = true)
	private String leader;

	@Column(name = "responsibleID", nullable = true)
	private Integer responsibleID;

	@Column(name = "contactInformation", nullable = true)
	private String contactInformation;

	@Column(name = "dynamics", nullable = true)
	private String dynamics;

	@Column(name = "sex", nullable = true)
	private String sex;

	@Column(name = "nation", nullable = true)
	private String nation;

	@Column(name = "birth", nullable = true)
	private String birth;

	@Column(name = "address", nullable = true)
	private String address;

	@Column(name = "idNum", nullable = true)
	private String idNum;

	@Column(name = "photo", nullable = true)
	private String photo;

	@Column(name = "attachment", nullable = true)
	private String attachment;

	@Column(name = "ask", nullable = true)
	private String ask;

	@Column(name = "issueProperty", nullable = true)
	private String issueProperty;

	@Column(name = "issueContent", nullable = true)
	private String issueContent;

	@Column(name = "territory", nullable = true)
	private String territory;

	@Column(name = "directorDepartment", nullable = true)
	private String directorDepartment;

	@Column(name = "addTime", nullable = true)
	private Date addTime;

	@Column(name = "addBy", nullable = true)
	private String addBy;

	@Column(name = "lastUpdateTime", nullable = true)
	private Date lastUpdateTime;

	@Column(name = "lastUpdateBy", nullable = true)
	private String lastUpdateBy;

	@Column(name = "isDelete", nullable = true)
	private Integer isDelete;

	@Column(name = "days", nullable = true)
	private String days;

	@Column(name = "fraction", nullable = true)
	private String fraction;

	public int getKeyPersonID() {
		return keyPersonID;
	}

	public void setKeyPersonID(int keyPersonID) {
		this.keyPersonID = keyPersonID;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getFraction() {
		return fraction;
	}

	public void setFraction(String fraction) {
		this.fraction = fraction;
	}

	public String getKeypersonname() {
		return keypersonname;
	}

	public void setKeypersonname(String keypersonname) {
		this.keypersonname = keypersonname;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getPopedom() {
		return popedom;
	}

	public void setPopedom(String popedom) {
		this.popedom = popedom;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public Integer getResponsibleID() {
		return responsibleID;
	}

	public void setResponsibleID(Integer responsibleID) {
		this.responsibleID = responsibleID;
	}

	public String getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}

	public String getDynamics() {
		return dynamics;
	}

	public void setDynamics(String dynamics) {
		this.dynamics = dynamics;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getAsk() {
		return ask;
	}

	public void setAsk(String ask) {
		this.ask = ask;
	}

	public String getIssueProperty() {
		return issueProperty;
	}

	public void setIssueProperty(String issueProperty) {
		this.issueProperty = issueProperty;
	}

	public String getIssueContent() {
		return issueContent;
	}

	public void setIssueContent(String issueContent) {
		this.issueContent = issueContent;
	}

	public String getTerritory() {
		return territory;
	}

	public void setTerritory(String territory) {
		this.territory = territory;
	}

	public String getDirectorDepartment() {
		return directorDepartment;
	}

	public void setDirectorDepartment(String directorDepartment) {
		this.directorDepartment = directorDepartment;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getAddBy() {
		return addBy;
	}

	public void setAddBy(String addBy) {
		this.addBy = addBy;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

}
