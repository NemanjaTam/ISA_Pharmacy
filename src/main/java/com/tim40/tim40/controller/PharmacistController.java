package com.tim40.tim40.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim40.tim40.service.PharmacistService;

@RestController
@RequestMapping(value = "api/pharmacist")
public class PharmacistController {
	
	private PharmacistService pharmacistService;

    @Autowired
	public PharmacistController(PharmacistService pharmacistService) {
		this.pharmacistService = pharmacistService;
	}
	
}
