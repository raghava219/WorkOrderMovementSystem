package com.quest.workorder.main.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quest.workorder.main.repository.WorkOrderMovementRepository;

@Service
public class WorkOrderMovementService {

	private final WorkOrderMovementRepository workOrderMovementRepository; 
	
	@Autowired
	public WorkOrderMovementService(WorkOrderMovementRepository workOrderMovementRepository) {
		this.workOrderMovementRepository = workOrderMovementRepository;
	}

	public List<>
}
