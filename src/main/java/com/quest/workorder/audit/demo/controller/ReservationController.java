package com.quest.workorder.audit.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.quest.workorder.main.business.service.WorkOrderMovementService;
import com.quest.workorder.main.domain.WorkOrderMovementAndUserAssociated;

@Controller
@RequestMapping(value="/reservations")
public class ReservationController {

	@Autowired
	private WorkOrderMovementService workOrderMovementService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getReservation(@RequestParam(value="date", required=false)String dateString, Model model) {
		List<WorkOrderMovementAndUserAssociated> wrkOrdMmtAndAssList = this.workOrderMovementService.getRoomReservationsForDate(dateString);
		
		model.addAttribute("wrkReservations", wrkOrdMmtAndAssList);
		
		return "reservations";
	}
	
}
