package com.quest.workorder.main.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.quest.workorder.audit.demo.listener.WorkOrderTaskListener;

@Table(name = "tbl_workorder_task", catalog = "Work_Order")
@Entity
@EntityListeners(WorkOrderTaskListener.class)
public class WorkOrderTask implements Serializable{

	private static final long serialVersionUID = 8371838234497876200L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "task_id")
	private long taskId;

	@Column(name = "task_type")
	private String taskType = null;

	@Column(name = "task_status")
	private String taskStatus = null;

	@Column(name = "created_by")
	private String createdBy = null;

	@Column(name = "creation_date")
	private Timestamp creationDate = null;

	@Column(name = "priority")
	private String priority = null;

	@Column(name = "from_location")
	private String fromLocation = null;

	@Column(name = "to_location")
	private String toLocation = null;

	@Transient
	@Column(name = "work_id")
	private long workId;
	
	public long getWorkId() {
		return workId;
	}

	public void setWorkId(long workId) {
		this.workId = workId;
	}
	
	public WorkOrderTask() {
	}

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	/*public WorkOrderMovement getWorkOrderMovement() {
		return workOrderMovement;
	}

	public void setWorkOrderMovement(WorkOrderMovement workOrderMovement) {

		this.workOrderMovement = workOrderMovement;
	}*/

}
