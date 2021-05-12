package com.tim40.tim40.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim40.tim40.service.ConsultationService;

@RestController
@RequestMapping(value = "api/consultation")
public class ConsultationController {

	private ConsultationService consultationService;

	@Autowired
	public ConsultationController(ConsultationService consultationService) {
		this.consultationService = consultationService;
	}
	
}
