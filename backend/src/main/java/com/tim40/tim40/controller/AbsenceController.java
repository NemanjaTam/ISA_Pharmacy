package com.tim40.tim40.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim40.tim40.dto.AbsenceCheckDTO;
import com.tim40.tim40.dto.AbsenceDTO;
import com.tim40.tim40.service.AbsenceService;

@RestController
@RequestMapping(value = "api/absence")
public class AbsenceController {
	
	private AbsenceService absenceService;

	@Autowired
	public AbsenceController(AbsenceService absenceService) {
		this.absenceService = absenceService;
	}
	
	@PostMapping(value = "/request-absence", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AbsenceDTO> requestAbsence(@RequestBody AbsenceDTO absenceDTO) throws Exception{
		return absenceService.requestAbsence(absenceDTO);
	}
	
	@PostMapping(value = "/check-is-user-on-absence", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> checkIsUserOnAbsence(@RequestBody AbsenceCheckDTO absenceCheckDTO) throws Exception{
		return absenceService.checkIsUserOnAbsence(absenceCheckDTO.getSelectedDate(), absenceCheckDTO.getUserId());
	}
}
