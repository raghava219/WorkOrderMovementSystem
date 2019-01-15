package com.quest.workorder.audit.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quest.workorder.audit.demo.exception.ResourceNotFoundException;
import com.quest.workorder.audit.domain.AuditOfWorkOrderMovementEntity;
import com.quest.workorder.audit.repository.WorkOrderMovementHistory;
import com.quest.workorder.main.entity.WorkOrderMovement;
import com.quest.workorder.main.repository.WorkOrderMovementRepository;


@RestController
@RequestMapping("/clients")
public class WorkOrderMvmtAuditController {

	@Autowired
    private WorkOrderMovementRepository workOrderMovementRepository;
	
	@Autowired
    private WorkOrderMovementHistory workOrderMovementHistory;
	
	
    @GetMapping("/WorkOrders")
    public Page<WorkOrderMovement> getWorkOrderMovements(Pageable pageable) {
        return workOrderMovementRepository.findAll(pageable);
    }
    
    @GetMapping("/WorkOrderHistDetails")
    public Page<AuditOfWorkOrderMovementEntity> getWorkOrderHistDetails(Pageable pageable) {
        return workOrderMovementHistory.findAll(pageable);
    }
    
    @GetMapping("/WorkOrder/{workid}")
    public Optional<WorkOrderMovement> getWorkOrderMovement(@PathVariable long workid) {
    	Optional<WorkOrderMovement> optionalWrkOrdMvmtById = workOrderMovementRepository.findById(workid);
        if (!optionalWrkOrdMvmtById.isPresent()) {
            throw new ResourceNotFoundException("The Work Order you are looking for is not available.");
        }
        return optionalWrkOrdMvmtById;
    }
     
    @GetMapping("/WorkOrderHistDetail/{audWorkId}")
    public List<AuditOfWorkOrderMovementEntity> getWorkOrderHistDetail(@PathVariable long audWorkId) {
        return workOrderMovementHistory.getAuditEntryByAudWorkId(audWorkId);
    }
    
    @PostMapping("/WorkOrders")
    public void createWorkOrder(@Valid @RequestBody WorkOrderMovement workOrderMovement) {

		workOrderMovementRepository.save(workOrderMovement);
		workOrderMovementRepository.flush();
    	
        //return workOrderMovementRepository.findAll();
    }
    
    @DeleteMapping("/WorkOrders/{workid}") 
    public void deleteWorkOrder(@PathVariable long workid) {
    	workOrderMovementRepository.deleteById(workid); 
    }
    
    @PutMapping("/WorkOrders")
    public void updateWorkOrder(@RequestBody WorkOrderMovement workOrderMovement) {
    	workOrderMovementRepository.save(workOrderMovement);
    }
    
}
