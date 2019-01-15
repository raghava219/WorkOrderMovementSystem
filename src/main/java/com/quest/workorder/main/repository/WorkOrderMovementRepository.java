package com.quest.workorder.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quest.workorder.main.entity.WorkOrderMovement;

@Repository
public interface WorkOrderMovementRepository extends JpaRepository<WorkOrderMovement, Long> {
	
	Optional<WorkOrderMovement> findById(Long id);
	
	WorkOrderMovement findByUnit(String unit);
	
}