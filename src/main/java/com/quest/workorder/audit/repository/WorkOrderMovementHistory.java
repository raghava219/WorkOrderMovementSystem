package com.quest.workorder.audit.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quest.workorder.audit.domain.AuditOfWorkOrderMovementEntity;

public interface WorkOrderMovementHistory extends JpaRepository<AuditOfWorkOrderMovementEntity, Long>{
	
	public List<AuditOfWorkOrderMovementEntity> getAuditEntryByAudWorkId(long audWorkId);
	
	Optional<AuditOfWorkOrderMovementEntity> findById(Long id);

}
