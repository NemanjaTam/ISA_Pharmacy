package com.tim40.tim40.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.tim40.tim40.dto.MedicationDTO;
import com.tim40.tim40.dto.MedicationQuantityDTO;
import com.tim40.tim40.dto.PatientAllergedDTO;
import com.tim40.tim40.dto.SearchMedicationDTO;
import com.tim40.tim40.dto.PurchaseOrderDTO;
import com.tim40.tim40.model.Medication;
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
	 public ResponseEntity<?> addMedication(@RequestBody MedicationDTO medicationDTO)
	{
		MedicationDTO createdMedication = medicationService.createMedication(medicationDTO);
		return new ResponseEntity<> (createdMedication, HttpStatus.OK);
 	}
	
	//ne menjati
	@PostMapping(value="/addMultiple", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<?> addMultipleMedication(@RequestBody List<MedicationQuantityDTO> newMedicationList)
	{ 
		List<Medication> newMeds = new ArrayList<Medication>();
		for(MedicationQuantityDTO medicationQuantity: newMedicationList) {
			Medication med = this.medicationService.createMedicationWithoutReplacement(medicationQuantity);
			newMeds.add(med);
		}
		
		return new ResponseEntity<> (newMeds, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)  
	 public ResponseEntity<List<MedicationDTO>> getAllMedications() throws Exception
	{
		List<MedicationDTO> medications = medicationService.getAllMedications();
		return new ResponseEntity<List<MedicationDTO>> (medications, HttpStatus.OK);
	}
	
	@PostMapping(value = "/search-medications", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MedicationDTO>> searchMedications(@RequestBody SearchMedicationDTO searchMedicationDTO) 
	{
		return medicationService.searchMedications(searchMedicationDTO);
	}

}
