package com.tim40.tim40.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim40.tim40.service.TherapyService;

@RestController
@RequestMapping(value = "api/therapy")
public class TherapyController {
	
	private TherapyService therapyService;

	@Autowired
	public TherapyController(TherapyService therapyService) {
		this.therapyService = therapyService;
	}
	
}
