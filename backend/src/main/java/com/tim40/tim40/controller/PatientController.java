package com.tim40.tim40.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tim40.tim40.dto.PatientAllergedDTO;
import com.tim40.tim40.service.PatientService;

@RestController
@RequestMapping(value = "api/patient")
public class PatientController {
	
	private PatientService patientService;

	@Autowired
	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}
	
	@RequestMapping(value = "/add-penalty/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> addPenalty(@PathVariable("id") Long id) {
		return patientService.addPenalty(id);
	}
	
	@PostMapping(value = "/is-allerged-on-medication", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> isPatientAllergedOnMedication(@RequestBody PatientAllergedDTO patientAllergedDTO ) 
	{
		return patientService.isAlergedOnMedication(patientAllergedDTO.getPatientId(), patientAllergedDTO.getMedicationId());
	}
	
}
