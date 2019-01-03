package com.quest.workorder.audit.demo.listener;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;

import com.quest.workorder.audit.domain.AuditOfWorkOrderMovementEntity;
import com.quest.workorder.main.domain.WorkOrderMovement;

public class WorkOrderMovementListener {

	private static final String INSERT = "INSERT";
	private static final String DELETE = "DELETE";
	private static final String UPDATE = "UPDATE";

	@PrePersist
	public void methodInvokedBeforePersist(WorkOrderMovement work) {
		try {
			System.out.println("persisting WorkOrderMovement with id = "
					+ work.getWorkId());
		} catch (Exception exe) {
			exe.printStackTrace();
		}
	}

	@PostPersist
	public void methodInvokedAfterPersist(WorkOrderMovement work)
			throws Exception {
		System.out.println("Persisted WorkOrderMovement with id = "
				+ work.getWorkId());

		EntityManagerFactory entityManagerFactory = BeanUtil.getBean(
				"auditEntityManagerFactory", EntityManagerFactory.class);
		EntityManager manager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();

		AuditOfWorkOrderMovementEntity audWrkEty = new AuditOfWorkOrderMovementEntity();

		Date myDate = new Date();
		Timestamp currentTimeStamp = new Timestamp(myDate.getTime());

		audWrkEty.setAudWorkId(work.getWorkId());
		AuditOfWorkOrderMovementEntity.setTempWorkId(work.getWorkId());
		audWrkEty.setUnitAudit(work.getUnit());
		audWrkEty.setTypeAudit(work.getType());
		audWrkEty.setStatusAudit(work.getStatus());
		audWrkEty.setStartDateAudit(work.getStartDate());
		audWrkEty.setEndDateAudit(work.getEndDate());
		audWrkEty.setShipperAudit(work.getShipper());
		audWrkEty.setRecordCreationTimestamp(currentTimeStamp);
		audWrkEty.setAuditOwner("Raghava");
		audWrkEty
				.setColumnsChanged("workid,unit,type,status,start_date,end_date,shipper");
		audWrkEty.setOperation(INSERT);

		try {
			transaction.begin();
			manager.persist(audWrkEty);
			manager.flush();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			try {
				if (manager != null) {
					manager.close();
				}
			} catch (Exception exe) {
				exe.printStackTrace();
			}
		}

	}

	@PreUpdate
	public void methodInvokedBeforeUpdate(WorkOrderMovement work) {
		System.out.println("Updating WorkOrderMovement with id = "
				+ work.getWorkId());

	}

	@PostUpdate
	public void methodInvokedAfterUpdate(WorkOrderMovement work) {
		System.out.println("Updated WorkOrderMovement with id = "
				+ work.getWorkId());

		EntityManagerFactory audEntityManagerFactory = BeanUtil.getBean(
				"auditEntityManagerFactory", EntityManagerFactory.class);
		EntityManager audManager = audEntityManagerFactory
				.createEntityManager();
		EntityTransaction audTransaction = audManager.getTransaction();

		EntityManagerFactory actialEntityManagerFactory = BeanUtil.getBean(
				"entityManagerFactory", EntityManagerFactory.class);
		EntityManager actualManager = actialEntityManagerFactory
				.createEntityManager();

		WorkOrderMovement workOrdDBMvmt = actualManager.find(
				WorkOrderMovement.class, work.getWorkId());

		StringBuffer changedColumns = new StringBuffer();

		AuditOfWorkOrderMovementEntity audWrkEty = new AuditOfWorkOrderMovementEntity();

		Date myDate = new Date();
		Timestamp currentTimeStamp = new Timestamp(myDate.getTime());

		audWrkEty.setAudWorkId(work.getWorkId());
		AuditOfWorkOrderMovementEntity.setTempWorkId(work.getWorkId());

		if (!work.getUnit().equals(workOrdDBMvmt.getUnit())) {
			audWrkEty.setUnitAudit(work.getUnit());
			changedColumns.append("unit,");
		} else {
			audWrkEty.setUnitAudit(workOrdDBMvmt.getUnit());
		}

		if (!work.getType().equals(workOrdDBMvmt.getType())) {
			audWrkEty.setTypeAudit(work.getType());
			changedColumns.append("type,");
		} else {
			audWrkEty.setTypeAudit(workOrdDBMvmt.getType());
		}

		if (!work.getStatus().equals(workOrdDBMvmt.getStatus())) {
			audWrkEty.setStatusAudit(work.getStatus());
			changedColumns.append("status,");
		} else {
			audWrkEty.setStatusAudit(workOrdDBMvmt.getStatus());
		}

		if (work.getStartDate().toLocalDate()
				.compareTo(workOrdDBMvmt.getStartDate().toLocalDate()) != 0) {
			audWrkEty.setStartDateAudit(work.getStartDate());
			changedColumns.append("start_date,");
		} else {
			audWrkEty.setStartDateAudit(workOrdDBMvmt.getStartDate());
		}

		if (work.getEndDate().toLocalDate()
				.compareTo(workOrdDBMvmt.getEndDate().toLocalDate()) != 0) {
			audWrkEty.setEndDateAudit(work.getEndDate());
			changedColumns.append("end_date,");
		} else {
			audWrkEty.setEndDateAudit(workOrdDBMvmt.getEndDate());
		}

		if (!work.getShipper().equals(workOrdDBMvmt.getShipper())) {
			audWrkEty.setShipperAudit(work.getShipper());
			changedColumns.append("shipper,");
		} else {
			audWrkEty.setShipperAudit(workOrdDBMvmt.getShipper());
		}
		
		// below fields from audit table
		audWrkEty.setRecordCreationTimestamp(currentTimeStamp);
		audWrkEty.setAuditOwner("Raghava");
		audWrkEty.setColumnsChanged(changedColumns.toString());
		audWrkEty.setOperation(UPDATE);

		try {
			audTransaction.begin();
			audManager.persist(audWrkEty);
			audManager.flush();
			audTransaction.commit();
		} catch (Exception ex) {
			if (audTransaction != null) {
				audTransaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			try {

				if (audManager != null) {
					audManager.close();
				}
				if (actualManager != null) {
					actualManager.close();
				}

			} catch (Exception exe) {
				exe.printStackTrace();
			}
		}

	}

	@PreRemove
	private void methodInvokedBeforeRemove(WorkOrderMovement work) {
		System.out.println("Removing WorkOrderMovement with id = "
				+ work.getWorkId());
	}

	@PostRemove
	public void methodInvokedAfterRemove(WorkOrderMovement work) {
		System.out.println("Removed WorkOrderMovement with id = "
				+ work.getWorkId());

		EntityManagerFactory entityManagerFactory = BeanUtil.getBean(
				"auditEntityManagerFactory", EntityManagerFactory.class);
		EntityManager manager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();

		AuditOfWorkOrderMovementEntity audWrkEty = new AuditOfWorkOrderMovementEntity();

		Date myDate = new Date();
		Timestamp currentTimeStamp = new Timestamp(myDate.getTime());

		audWrkEty.setAudWorkId(work.getWorkId());
		AuditOfWorkOrderMovementEntity.setTempWorkId(work.getWorkId());
		audWrkEty.setUnitAudit(work.getUnit());
		audWrkEty.setTypeAudit(work.getType());
		audWrkEty.setStatusAudit(work.getStatus());
		audWrkEty.setStartDateAudit(work.getStartDate());
		audWrkEty.setEndDateAudit(work.getEndDate());
		audWrkEty.setShipperAudit(work.getShipper());
		audWrkEty.setRecordCreationTimestamp(currentTimeStamp);
		audWrkEty.setAuditOwner("Raghava");
		audWrkEty
				.setColumnsChanged("workid,unit,type,status,start_date,end_date,shipper");

		audWrkEty.setOperation(DELETE);

		try {
			transaction.begin();
			manager.persist(audWrkEty);
			manager.flush();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			try {
				/*if (entityManagerFactory != null) {
					entityManagerFactory.close();
				}*/
				if (manager != null) {
					manager.close();
				}
			} catch (Exception exe) {
				exe.printStackTrace();
			}
		}
	}

}
