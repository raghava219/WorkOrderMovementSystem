package com.quest.workorder.main.domain;

import java.util.Date;

/*
 * This class is combination of "WorkOrderMovement" and "User" who has requested it. 
 */

public class WorkOrderMovementAndUserAssociated {

	private long movementId = 0;
    private long userId = 0;
	private String unit = null;
    private String type = null;
    private String firstName = null;
    private String lastName = null;
    private Date dateOfRequest = null;
	
    public long getMovementId() {
		return movementId;
	}
	public void setMovementId(long movementId) {
		this.movementId = movementId;
	}
    public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfRequest() {
		return dateOfRequest;
	}
	public void setDateOfRequest(Date dateOfRequest) {
		this.dateOfRequest = dateOfRequest;
	}
	
}
