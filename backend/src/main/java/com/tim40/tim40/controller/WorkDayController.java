package com.tim40.tim40.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim40.tim40.dto.AbsenceCheckDTO;
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
	
}
