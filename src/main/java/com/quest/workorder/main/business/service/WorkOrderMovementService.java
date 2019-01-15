package com.quest.workorder.main.business.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.quest.workorder.main.domain.WorkOrderMovementAndUserAssociated;
import com.quest.workorder.main.entity.User;
import com.quest.workorder.main.entity.WorkOrderMovement;
import com.quest.workorder.main.entity.WorkOrderReservation;
import com.quest.workorder.main.repository.UserRepository;
import com.quest.workorder.main.repository.WorkOrderMovementRepository;
import com.quest.workorder.main.repository.WorkOrderReservationRepository;

@Service
public class WorkOrderMovementService {

	//@Autowired
	private final WorkOrderMovementRepository workOrderMovementRepository;
	//@Autowired
	private final WorkOrderReservationRepository workOrderReservationRepository;
	//@Autowired
	private final UserRepository userRepository;

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	public WorkOrderMovementService(WorkOrderMovementRepository workOrderMovementRepository,
			WorkOrderReservationRepository workOrderReservationRepository, UserRepository userRepository) {
		this.workOrderMovementRepository = workOrderMovementRepository;
		this.workOrderReservationRepository = workOrderReservationRepository;
		this.userRepository = userRepository;
	}

	public List<WorkOrderMovementAndUserAssociated> getRoomReservationsForDate(String dateString) {
		
		Date date = this.createDateFromDateString(dateString);
		
		Iterable<WorkOrderMovement> movements = this.workOrderMovementRepository.findAll();
		Map<Long, WorkOrderMovementAndUserAssociated> roomReservationMap = new HashMap<>();
		movements.forEach(movement -> {
			WorkOrderMovementAndUserAssociated movementReservation = new WorkOrderMovementAndUserAssociated();
			movementReservation.setMovementId(movement.getWorkId());
			movementReservation.setUnit(movement.getUnit());
			movementReservation.setType(movement.getType());
			roomReservationMap.put(movement.getWorkId(), movementReservation);
		});
		
		Iterable<WorkOrderReservation> reservations = this.workOrderReservationRepository
				.findByDate(new java.sql.Date(date.getTime()));
		
		if (null != reservations) {
			reservations.forEach(reservation -> {
				User user = this.userRepository.findById(reservation.getUserId()).orElse(null);
				if (null != user) {
					WorkOrderMovementAndUserAssociated roomReservation = roomReservationMap.get(reservation.getWorkId());
					roomReservation.setDateOfRequest(date);
					roomReservation.setFirstName(user.getFirstName());
					roomReservation.setLastName(user.getLastName());
					roomReservation.setUserId(user.getId());
				}
			});
		}
		
		List<WorkOrderMovementAndUserAssociated> roomReservations = new ArrayList<>();
		for (Long roomId : roomReservationMap.keySet()) {
			roomReservations.add(roomReservationMap.get(roomId));
		}
		return roomReservations;
	}
	
    private Date createDateFromDateString(String dateString){
        Date date = null;
        if(null!=dateString) {
            try {
                date = DATE_FORMAT.parse(dateString);
            }catch(ParseException pe){
                date = new Date();
            }
        }else{
            date = new Date();
        }
        return date;
    }
}
