package com.tim40.tim40.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim40.tim40.dto.MedicationDTO;
import com.tim40.tim40.service.MedicationService;

@RestController
@RequestMapping(value = "api/medication")
public class MedicationController {
	
	private MedicationService medicationService;

	@Autowired
	public MedicationController(MedicationService medicationService) {
		this.medicationService = medicationService;
	}
	
	@PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<MedicationDTO> addMedication(@RequestBody MedicationDTO medicationDTO) throws Exception
	{
		MedicationDTO createdMedication = medicationService.createMedication(medicationDTO);
		return new ResponseEntity<MedicationDTO> (createdMedication, HttpStatus.OK);
 	}
	
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)  
	 public ResponseEntity<List<MedicationDTO>> getAllMedications() throws Exception
	{
		List<MedicationDTO> medications = medicationService.getAllMedications();
		return new ResponseEntity<List<MedicationDTO>> (medications, HttpStatus.OK);
	}
}
