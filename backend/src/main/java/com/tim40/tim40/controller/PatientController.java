package com.tim40.tim40.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
