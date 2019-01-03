package com.quest.workorder.audit.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tbl_workorder_audit_task", catalog = "Audit_Work_Order")
@Entity
public class AuditOfWorkOrderTaskEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "audit_id")
	private long auditId = 0;

	@Column(name = "aud_task_id")
	private long audTaskId = 0;

	@Column(name = "aud_work_id")
	private long audWorkId = 0;

	@Column(name = "aud_task_type")
	private String audTaskType = null;

	@Column(name = "aud_task_status")
	private String audTaskStatus = null;

	@Column(name = "aud_created_by")
	private String audCreatedBy = null;

	@Column(name = "aud_creation_date")
	private Timestamp audCreationDate = null;

	@Column(name = "aud_priority")
	private String audPriority = null;

	@Column(name = "aud_from_location")
	private String audFromLocation = null;

	@Column(name = "aud_to_location")
	private String audToLocation = null;

	@Column(name = "record_creation_timestamp")
	private Timestamp recordCreationTimestamp = null;

	@Column(name = "audit_owner")
	private String auditOwner = null;

	@Column(name = "columns_changed")
	private String columnsChanged = null;

	@Column(name = "operation")
	private String operation = null;

	public long getAuditId() {
		return auditId;
	}

	public long getAudTaskId() {
		return audTaskId;
	}

	public long getAudWorkId() {
		return audWorkId;
	}

	public String getAudTaskType() {
		return audTaskType;
	}

	public String getAudTaskStatus() {
		return audTaskStatus;
	}

	public String getAudCreatedBy() {
		return audCreatedBy;
	}

	public Timestamp getAudCreationDate() {
		return audCreationDate;
	}

	public String getAudPriority() {
		return audPriority;
	}

	public String getAudFromLocation() {
		return audFromLocation;
	}

	public String getAudToLocation() {
		return audToLocation;
	}

	public Timestamp getRecordCreationTimestamp() {
		return recordCreationTimestamp;
	}

	public String getAuditOwner() {
		return auditOwner;
	}

	public String getColumnsChanged() {
		return columnsChanged;
	}

	public String getOperation() {
		return operation;
	}

	public void setAuditId(long auditId) {
		this.auditId = auditId;
	}

	public void setAudTaskId(long audTaskId) {
		this.audTaskId = audTaskId;
	}

	public void setAudWorkId(long audWorkId) {
		this.audWorkId = audWorkId;
	}

	public void setAudTaskType(String audTaskType) {
		this.audTaskType = audTaskType;
	}

	public void setAudTaskStatus(String audTaskStatus) {
		this.audTaskStatus = audTaskStatus;
	}

	public void setAudCreatedBy(String audCreatedBy) {
		this.audCreatedBy = audCreatedBy;
	}

	public void setAudCreationDate(Timestamp audCreationDate) {
		this.audCreationDate = audCreationDate;
	}

	public void setAudPriority(String audPriority) {
		this.audPriority = audPriority;
	}

	public void setAudFromLocation(String audFromLocation) {
		this.audFromLocation = audFromLocation;
	}

	public void setAudToLocation(String audToLocation) {
		this.audToLocation = audToLocation;
	}

	public void setRecordCreationTimestamp(Timestamp recordCreationTimestamp) {
		this.recordCreationTimestamp = recordCreationTimestamp;
	}

	public void setAuditOwner(String auditOwner) {
		this.auditOwner = auditOwner;
	}

	public void setColumnsChanged(String columnsChanged) {
		this.columnsChanged = columnsChanged;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}
