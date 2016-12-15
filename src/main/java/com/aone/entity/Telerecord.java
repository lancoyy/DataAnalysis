package com.aone.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="telerecord")
public class Telerecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="senderNumber", length = 50)
	private String source;
	
	@Column(name="receiverNumber", length = 50)
	private String target;
	
	@Column(name="startTime", nullable = true, length = 20)
	private Date startTime;
	
	@Column(name="endTime", nullable = true, length = 20)
	private Date endTime;
	
	@Column(name="senderBaseStation", nullable = true, length = 20)
	private Integer senderBaseStation;
	
	@Column(name="receiverBaseStation", nullable = true, length = 20)
	private Integer receiverBaseStation;
	
	@Column(name="senderSector", nullable = true, length = 2)
	private String senderSector;
	
	@Column(name="receiverSector", nullable = true, length = 2)
	private String receiverSector;
	

	public int getSenderBaseStation() {
		return senderBaseStation;
	}

	public void setSenderBaseStation(int senderBaseStation) {
		this.senderBaseStation = senderBaseStation;
	}

	public int getReceiverBaseStation() {
		return receiverBaseStation;
	}

	public void setReceiverBaseStation(int receiverBaseStation) {
		this.receiverBaseStation = receiverBaseStation;
	}

	public String getSenderSector() {
		return senderSector;
	}

	public void setSenderSector(String senderSector) {
		this.senderSector = senderSector;
	}

	public String getReceiverSector() {
		return receiverSector;
	}

	public void setReceiverSector(String receiverSector) {
		this.receiverSector = receiverSector;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
}
