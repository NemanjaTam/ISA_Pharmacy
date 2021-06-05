package com.tim40.tim40.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tim40.tim40.dto.PatientAllergedDTO;
import com.tim40.tim40.dto.PharmacyDTO;
import com.tim40.tim40.model.Medication;
import com.tim40.tim40.service.PharmacyService;

@RestController
@RequestMapping(value = "api/pharmacy")
public class PharmacyController {
	
	private PharmacyService pharmacyService;

	@Autowired
	public PharmacyController(PharmacyService pharmacyService) {
		this.pharmacyService = pharmacyService;
	}
	
	@PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addPharmacy(@RequestBody PharmacyDTO pharmacyDTO ) 
	{
		PharmacyDTO createdPharmacy = pharmacyService.createPharmacy(pharmacyDTO);
		return new ResponseEntity<> (createdPharmacy, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-medications/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Medication>> getAllMedications(@PathVariable("id") Long id) {
		return pharmacyService.getAllMedications(id);
	}
	
	@PostMapping(value = "/is-medication-available", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> isMedicationAvailable(@RequestBody PatientAllergedDTO patientAllergedDTO ) 
	{
		return pharmacyService.isMedicationAvailable(patientAllergedDTO.getPatientId(), patientAllergedDTO.getMedicationId());
	}

}
