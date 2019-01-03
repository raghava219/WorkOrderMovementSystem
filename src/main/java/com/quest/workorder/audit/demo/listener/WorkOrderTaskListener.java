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

import com.quest.workorder.audit.domain.AuditOfWorkOrderMovementEntity;
import com.quest.workorder.audit.domain.AuditOfWorkOrderTaskEntity;
import com.quest.workorder.main.domain.WorkOrderMovement;
import com.quest.workorder.main.domain.WorkOrderTask;

public class WorkOrderTaskListener {

	private static final String INSERT = "INSERT";
	private static final String DELETE = "DELETE";
	private static final String UPDATE = "UPDATE";

	@PrePersist
	public void methodInvokedBeforePersist(WorkOrderTask task) {
		try {
			System.out.println("persisting WorkOrderTask with id = "
					+ task.getTaskId());
		} catch (Exception exe) {
			exe.printStackTrace();
		}
	}

	@PostPersist
	public void methodInvokedAfterPersist(WorkOrderTask task) throws Exception {
		System.out.println("Persisted WorkOrderTask with id = "
				+ task.getTaskId());

		EntityManagerFactory entityManagerFactory = BeanUtil.getBean(
				"auditEntityManagerFactory", EntityManagerFactory.class);
		EntityManager manager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();

		Date myDate = new Date();
		Timestamp currentTimeStamp = new Timestamp(myDate.getTime());

		AuditOfWorkOrderTaskEntity audWrkTskEty = new AuditOfWorkOrderTaskEntity();

		audWrkTskEty.setAudTaskId(task.getTaskId());
		audWrkTskEty.setAudWorkId(AuditOfWorkOrderMovementEntity
				.getTempWorkId());
		audWrkTskEty.setAudTaskType(task.getTaskType());
		audWrkTskEty.setAudTaskStatus(task.getTaskStatus());
		audWrkTskEty.setAudCreatedBy(task.getCreatedBy());
		audWrkTskEty.setAudCreationDate(currentTimeStamp);
		audWrkTskEty.setAudPriority(task.getPriority());
		audWrkTskEty.setAudFromLocation(task.getFromLocation());
		audWrkTskEty.setAudToLocation(task.getToLocation());
		audWrkTskEty.setRecordCreationTimestamp(currentTimeStamp);
		audWrkTskEty.setAuditOwner("Santosh");
		audWrkTskEty
				.setColumnsChanged("task_id,work_id,task_type,task_status,created_by,creation_date,priority,from_location,to_location");
		audWrkTskEty.setOperation(INSERT);

		try {
			transaction.begin();
			manager.persist(audWrkTskEty);
			manager.flush();
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		} finally {
			if (manager != null) {
				manager.close();
			}
		}

	}

	@PreUpdate
	public void methodInvokedBeforeUpdate(WorkOrderTask task) {
		System.out.println("Updating WorkOrderTask with id = "
				+ task.getTaskId());
		System.out.println("Updating WorkOrderTask with WorkId = "
				+ task.getWorkId());
	}

	@PostUpdate
	public void methodInvokedAfterUpdate(WorkOrderTask task) {
		System.out.println("Updated WorkOrderTask with id = "
				+ task.getTaskId());
		System.out.println("Updated WorkOrderTask with Workid = "
				+ task.getWorkId());

		EntityManagerFactory audEntityManagerFactory = BeanUtil.getBean(
				"auditEntityManagerFactory", EntityManagerFactory.class);
		EntityManager audManager = audEntityManagerFactory
				.createEntityManager();
		EntityTransaction audTransaction = audManager.getTransaction();

		EntityManagerFactory actialEntityManagerFactory = BeanUtil.getBean(
				"entityManagerFactory", EntityManagerFactory.class);
		EntityManager actualManager = actialEntityManagerFactory
				.createEntityManager();

		WorkOrderTask workOrdDBTask = actualManager.find(WorkOrderTask.class,
				task.getTaskId());

		StringBuffer changedColumns = new StringBuffer();

		AuditOfWorkOrderTaskEntity audWrkTaskEty = new AuditOfWorkOrderTaskEntity();

		Date myDate = new Date();
		Timestamp currentTimeStamp = new Timestamp(myDate.getTime());

		audWrkTaskEty.setAudTaskId(task.getTaskId());
		audWrkTaskEty.setAudWorkId(AuditOfWorkOrderMovementEntity.getTempWorkId()); // work_id

		if (!task.getTaskType().equals(workOrdDBTask.getTaskType())) {
			audWrkTaskEty.setAudTaskType(task.getTaskType());
			changedColumns.append("task_type,");
		} else {
			audWrkTaskEty.setAudTaskType(workOrdDBTask.getTaskType());
		}

		if (!task.getTaskStatus().equals(workOrdDBTask.getTaskStatus())) {
			audWrkTaskEty.setAudTaskStatus(task.getTaskStatus());
			changedColumns.append("task_status,");
		} else {
			audWrkTaskEty.setAudTaskStatus(workOrdDBTask.getTaskStatus());
		}

		if (!task.getTaskStatus().equals(workOrdDBTask.getTaskStatus())) {
			audWrkTaskEty.setAudCreatedBy(task.getCreatedBy());
			changedColumns.append("created_by,");
		} else {
			audWrkTaskEty.setAudCreatedBy(workOrdDBTask.getTaskStatus());
		}

		if (task.getCreationDate().toLocalDateTime()
				.compareTo(workOrdDBTask.getCreationDate().toLocalDateTime()) != 0) {
			audWrkTaskEty.setAudCreationDate(currentTimeStamp);
			changedColumns.append("creation_date,");
		} else {
			audWrkTaskEty.setAudCreationDate(workOrdDBTask.getCreationDate());
		}

		if (!task.getPriority().equals(workOrdDBTask.getPriority())) {
			audWrkTaskEty.setAudPriority(task.getPriority());
			changedColumns.append("priority,");
		} else {
			audWrkTaskEty.setAudPriority(workOrdDBTask.getPriority());
		}

		if (!task.getFromLocation().equals(workOrdDBTask.getFromLocation())) {
			audWrkTaskEty.setAudFromLocation(task.getFromLocation());
			changedColumns.append("from_location,");
		} else {
			audWrkTaskEty.setAudFromLocation(workOrdDBTask.getFromLocation());
		}

		if (!task.getToLocation().equals(workOrdDBTask.getToLocation())) {
			audWrkTaskEty.setAudToLocation(task.getToLocation());
			changedColumns.append("to_location,");
		} else {
			audWrkTaskEty.setAudToLocation(workOrdDBTask.getToLocation());
		}

		audWrkTaskEty.setRecordCreationTimestamp(currentTimeStamp);
		audWrkTaskEty.setAuditOwner("Raghava");
		audWrkTaskEty.setColumnsChanged(changedColumns.toString());
		audWrkTaskEty.setOperation(UPDATE);

		try {
			audTransaction.begin();
			audManager.persist(audWrkTaskEty);
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
	private void methodInvokedBeforeRemove(WorkOrderTask task) {
		System.out.println("Removing WorkOrderTask with id = "
				+ task.getTaskId());
	}

	@PostRemove
	public void methodInvokedAfterRemove(WorkOrderTask task) {
		System.out.println("Removed WorkOrderTask with id = "
				+ task.getTaskId());

		EntityManagerFactory entityManagerFactory = BeanUtil.getBean(
				"auditEntityManagerFactory", EntityManagerFactory.class);
		EntityManager manager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();

		Date myDate = new Date();
		Timestamp currentTimeStamp = new Timestamp(myDate.getTime());

		AuditOfWorkOrderTaskEntity audWrkTskEty = new AuditOfWorkOrderTaskEntity();

		audWrkTskEty.setAudTaskId(task.getTaskId());
		audWrkTskEty.setAudWorkId(AuditOfWorkOrderMovementEntity
				.getTempWorkId());
		audWrkTskEty.setAudTaskType(task.getTaskType());
		audWrkTskEty.setAudTaskStatus(task.getTaskStatus());
		audWrkTskEty.setAudCreatedBy(task.getCreatedBy());
		audWrkTskEty.setAudCreationDate(currentTimeStamp);
		audWrkTskEty.setAudPriority(task.getPriority());
		audWrkTskEty.setAudFromLocation(task.getFromLocation());
		audWrkTskEty.setAudToLocation(task.getToLocation());
		audWrkTskEty.setRecordCreationTimestamp(currentTimeStamp);
		audWrkTskEty.setAuditOwner("Krishna");
		audWrkTskEty
				.setColumnsChanged("task_id,work_id,task_type,task_status,created_by,creation_date,priority,from_location,to_location");
		audWrkTskEty.setOperation(DELETE);

		try {
			transaction.begin();
			manager.persist(audWrkTskEty);
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

}
