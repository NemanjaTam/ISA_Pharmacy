package com.tim40.tim40.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim40.tim40.service.AbsenceService;

@RestController
@RequestMapping(value = "api/absence")
public class AbsenceController {
	
	private AbsenceService absenceService;

	@Autowired
	public AbsenceController(AbsenceService absenceService) {
		this.absenceService = absenceService;
	}
	
}
