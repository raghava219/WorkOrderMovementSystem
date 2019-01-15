package com.quest.workorder.main.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quest.workorder.main.entity.WorkOrderReservation;

@Repository
public interface WorkOrderReservationRepository extends CrudRepository<WorkOrderReservation, Long> {
	
	List<WorkOrderReservation> findByDate(Date date);
	
}