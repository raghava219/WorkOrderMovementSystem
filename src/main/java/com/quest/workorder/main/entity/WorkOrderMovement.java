package com.quest.workorder.main.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.quest.workorder.audit.demo.listener.WorkOrderMovementListener;

@Table(name = "tbl_workorder_primary", catalog = "Work_Order")
@Entity
@EntityListeners(WorkOrderMovementListener.class)
@TypeDef(name = "JsonDataUserType", typeClass = JsonDataUserType.class)
public class WorkOrderMovement implements Serializable {

	private static final long serialVersionUID = 8271335877943894531L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "workid")
	private long workId = 0;

	@Column(name = "unit")
	private String unit = null;

	@Column(name = "type")
	private String type = null;

	@Column(name = "status")
	private String status = null;

	@Column(name = "start_date")
	private Date startDate = null;

	@Column(name = "end_date")
	private Date endDate = null;

	@Column(name = "shipper")
	private String shipper = null;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "work_id", referencedColumnName = "workid")
	private List<WorkOrderTask> wrkOrdTasks = null;

	@Type(type = "JsonDataUserType")
	private Map<String, String> location = null;

	public WorkOrderMovement() {
	}

	public List<WorkOrderTask> getWrkOrdTasks() {
		return wrkOrdTasks;
	}

	public void setWrkOrdTasks(List<WorkOrderTask> wrkOrdTasks) {
		this.wrkOrdTasks = wrkOrdTasks;
	}

	public long getWorkId() {
		return workId;
	}

	public void setWorkId(long workId) {
		this.workId = workId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	public Map<String, String> getLocation() {
		return location;
	}

	public void setLocation(Map<String, String> location) {
		this.location = location;
	}

}
