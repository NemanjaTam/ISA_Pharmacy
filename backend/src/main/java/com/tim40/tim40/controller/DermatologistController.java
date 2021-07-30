package com.tim40.tim40.controller;

import java.util.List;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tim40.tim40.model.*;
import com.tim40.tim40.service.DermatologistService;

@RestController
@RequestMapping(value = "api/dermatologist")
public class DermatologistController {
	
	private DermatologistService dermatologistService;

	@Autowired
	public DermatologistController(DermatologistService dermatologistService) {
		this.dermatologistService = dermatologistService;
	}
	
	@RequestMapping(value = "/get-all/{id}", method = RequestMethod.GET)
	public ResponseEntity<Set<Patient>> getAll(@PathVariable("id") Long id) {
		return dermatologistService.getAllPatients(id);
	}
	
	@RequestMapping(value="/getalldermatologist/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Dermatologist>> getAllByPharmacyID(@PathVariable("id") Long id){
	 return dermatologistService.getAllDermatologists(id);
	}
	
}
