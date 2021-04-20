package com.tim40.tim40.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim40.tim40.service.MedicationService;

@RestController
@RequestMapping(value = "api/medication")
public class MedicationController {
	
	private MedicationService medicationService;

	@Autowired
	public MedicationController(MedicationService medicationService) {
		this.medicationService = medicationService;
	}
	
}
