package com.tim40.tim40.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tim40.tim40.dto.AbsenceCheckDTO;
import com.tim40.tim40.dto.NewWorkDaysDTO;
import com.tim40.tim40.model.Period;
import com.tim40.tim40.model.WorkDay;
import com.tim40.tim40.service.WorkDayService;

@RestController
@RequestMapping(value = "api/workday")
public class WorkDayController {
	
	private WorkDayService workDayService;

	@Autowired
	public WorkDayController(WorkDayService workDayService) {
		this.workDayService = workDayService;
	}
	
	@PostMapping(value = "/check-is-user-working", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> checkIsUserWorking(@RequestBody AbsenceCheckDTO absenceCheckDTO) throws Exception{
		return workDayService.checkIsUserWorking(absenceCheckDTO.getSelectedDate(), absenceCheckDTO.getUserId());
	}
	
	@GetMapping(value="/get-working-non-absence-days/{id}/{idderm}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Period>> getWorkingNonAbsenceDays(@PathVariable("idderm") Long dermatologistId,@PathVariable("id") Long pharmacyId,@RequestHeader("usertype") String type){
		if("PHARMACY_ADMINISTRATOR".equals(type)){
			return new ResponseEntity<List<Period>>(this.workDayService.getAllWorkingDays(dermatologistId, pharmacyId), HttpStatus.OK);
			}
			else {
				return new ResponseEntity<List<Period>>(HttpStatus.UNAUTHORIZED);
			}
	} 
	//TRANSAKCIJA
	@Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_UNCOMMITTED)
	@PostMapping(value="/create-working-days/{id}/{iduser}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> createWorkDays(@PathVariable("iduser") Long id,@PathVariable("id") Long pharmacyId,@RequestHeader("usertype") String type,@RequestBody NewWorkDaysDTO dto){
		if("PHARMACY_ADMINISTRATOR".equals(type)){
			return new ResponseEntity<Integer>(this.workDayService.createWorkDays(dto,id, pharmacyId), HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Integer>(HttpStatus.UNAUTHORIZED);
			}
	} 

	
}
