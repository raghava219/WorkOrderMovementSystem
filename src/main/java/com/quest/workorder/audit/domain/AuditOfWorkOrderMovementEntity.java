package com.quest.workorder.audit.domain;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.quest.workorder.main.domain.WorkOrderTask;

@Table(name="tbl_workorder_audit_primary", catalog="Audit_Work_Order")
@Entity
public class AuditOfWorkOrderMovementEntity {
	
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="audit_id")
	private long auditId = 0;
    
    private static long tempWorkId = 0;
    
    @Column(name="audworkid")
	private long audWorkId = 0;
    
    @Column(name="unit_aud")
    private String unitAudit = null;
    
    @Column(name="type_aud")
	private String typeAudit = null;
    
    @Column(name="status_aud")
    private String statusAudit = null;
    
    @Column(name="start_date_aud")
    private Date startDateAudit = null;
    
    @Column(name="end_date_aud")
	private Date endDateAudit = null;
    
    @Column(name="shipper_aud")
	private String shipperAudit = null;
    
    @Column(name="record_creation_timestamp")
	private Timestamp recordCreationTimestamp = null;
    
    @Column(name="audit_owner")
    private String auditOwner = null;
    
    @Column(name="columns_changed")
	private String columnsChanged = null;
    
    @Column(name="operation")
    private String operation = null;
    
    @Column(name="aud_location")
    @JsonRawValue
    private String audLocation = null;
    
    public static void setTempWorkId(long tempWorkId) {
		AuditOfWorkOrderMovementEntity.tempWorkId = tempWorkId;
	}

	public static long getTempWorkId() {
		return tempWorkId;
	}

	public long getAuditId() {
		return auditId;
	}

	public void setAuditId(long auditId) {
		this.auditId = auditId;
	}

	public long getAudWorkId() {
		return audWorkId;
	}

	public void setAudWorkId(long audWorkId) {
		this.audWorkId = audWorkId;
	}

	public String getUnitAudit() {
		return unitAudit;
	}

	public void setUnitAudit(String unitAudit) {
		this.unitAudit = unitAudit;
	}

	public String getTypeAudit() {
		return typeAudit;
	}

	public void setTypeAudit(String typeAudit) {
		this.typeAudit = typeAudit;
	}

	public String getStatusAudit() {
		return statusAudit;
	}

	public void setStatusAudit(String statusAudit) {
		this.statusAudit = statusAudit;
	}

	public Date getStartDateAudit() {
		return startDateAudit;
	}

	public void setStartDateAudit(Date startDateAudit) {
		this.startDateAudit = startDateAudit;
	}

	public Date getEndDateAudit() {
		return endDateAudit;
	}

	public void setEndDateAudit(Date endDateAudit) {
		this.endDateAudit = endDateAudit;
	}

	public String getShipperAudit() {
		return shipperAudit;
	}

	public void setShipperAudit(String shipperAudit) {
		this.shipperAudit = shipperAudit;
	}

	public Timestamp getRecordCreationTimestamp() {
		return recordCreationTimestamp;
	}

	public void setRecordCreationTimestamp(Timestamp recordCreationTimestamp) {
		this.recordCreationTimestamp = recordCreationTimestamp;
	}

	public String getAuditOwner() {
		return auditOwner;
	}

	public void setAuditOwner(String auditOwner) {
		this.auditOwner = auditOwner;
	}

	public String getColumnsChanged() {
		return columnsChanged;
	}

	public void setColumnsChanged(String columnsChanged) {
		this.columnsChanged = columnsChanged;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getAudLocation() {
		return audLocation;
	}

	public void setAudLocation(String audLocation) {
		this.audLocation = audLocation;
	}
	
	
	

}
