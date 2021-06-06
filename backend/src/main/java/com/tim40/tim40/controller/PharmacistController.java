package com.tim40.tim40.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tim40.tim40.model.Patient;
import com.tim40.tim40.service.PharmacistService;

@RestController
@RequestMapping(value = "api/pharmacist")
public class PharmacistController {
	
	private PharmacistService pharmacistService;

    @Autowired
	public PharmacistController(PharmacistService pharmacistService) {
		this.pharmacistService = pharmacistService;
	}
	
	@RequestMapping(value = "/get-all/{id}", method = RequestMethod.GET)
	public ResponseEntity<Set<Patient>> getAll(@PathVariable("id") Long id) {
		return pharmacistService.getAllPatients(id);
	}
    
}
