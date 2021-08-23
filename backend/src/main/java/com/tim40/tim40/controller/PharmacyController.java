package com.tim40.tim40.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tim40.tim40.dto.MedicationDTO;
import com.tim40.tim40.dto.MedicationQuantityDTO;
import com.tim40.tim40.dto.PatientAllergedDTO;
import com.tim40.tim40.dto.PharmacyDTO;
import com.tim40.tim40.dto.PharmacyProfileDTO;
import com.tim40.tim40.dto.PurchaseOrderDTO;
import com.tim40.tim40.model.Medication;
import com.tim40.tim40.model.Pharmacy;
import com.tim40.tim40.model.PurchaseOrder;
import com.tim40.tim40.model.QuantityMedication;
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
	//ne menjati koristi se za funkcionalnost 
	@GetMapping(value = "/getpharmacy/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public PharmacyProfileDTO getPharmacyById(@PathVariable(name="id") String stringId){
		long id = Long.valueOf(stringId);
        PharmacyProfileDTO pharmacyDTO = new PharmacyProfileDTO();
        Pharmacy pharmacy = pharmacyService.getById(id);
        pharmacyDTO.setName(pharmacy.getName());
        pharmacyDTO.setAvgRating(pharmacy.getAvgRating());
        pharmacyDTO.setAddress(pharmacy.getAddress());

        return pharmacyDTO;
    }
	//ne menjati
	@GetMapping(value = "/getsubscribersbypharmacyid/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<String> getAllSubscribers(@PathVariable(name="id") String stringId){
		long id = Long.valueOf(stringId);
		return this.pharmacyService.getAllSubscribers(id);
	}
	
	//ne menjati
	@GetMapping(value = "/getpharmacyidbyuser/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Long getPharmacyIdbyUserId(@PathVariable(name="id") String stringId) {
		long id = Long.valueOf(stringId);
		return this.pharmacyService.getPharmacyIdByUserId(id);
		
	}
	//ne menjati
	@PostMapping(value="purchaseorder-create/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public boolean CreatePurchaseOrder(@PathVariable(name="id") String stringId,@RequestBody PurchaseOrderDTO dto) {
		long id = Long.valueOf(stringId);
		Pharmacy pharmacy = this.pharmacyService.getById(id);	
		return this.pharmacyService.CreatePurchaseOrder(pharmacy,dto);
		
	}
	
	@PostMapping(value="purchaseorder-create-new-medication/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public boolean CreatePurchaseOrderForNewMedication(@PathVariable(name="id") String stringId,@RequestBody PurchaseOrderDTO dto) {
		long id = Long.valueOf(stringId);
		Pharmacy pharmacy = this.pharmacyService.getById(id);	
		return this.pharmacyService.CreatePurchaseOrderForNewMedication(pharmacy,dto);
		
	}
	@GetMapping(value = "/get-purchase-order/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Set<PurchaseOrder> getAllPurchaseOrders(@PathVariable("id") Long id){
	 Set<PurchaseOrder> purchaseOrders = this.pharmacyService.getAllPurchaseOrders(id);
		return purchaseOrders;
	}
	
	

}
