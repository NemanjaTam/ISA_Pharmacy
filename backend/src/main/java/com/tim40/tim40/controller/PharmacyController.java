package com.tim40.tim40.controller;

import java.util.List;

import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tim40.tim40.dto.AbsenceDetailedDTO;
import com.tim40.tim40.dto.AcceptOfferDTO;
import com.tim40.tim40.dto.DermatologistDetailsDTO;
import com.tim40.tim40.dto.DermatologistRatingDTO;
import com.tim40.tim40.dto.MedicationDTO;
import com.tim40.tim40.dto.MedicationQuantityDTO;
import com.tim40.tim40.dto.PatientAllergedDTO;
import com.tim40.tim40.dto.PharmacyDTO;
import com.tim40.tim40.dto.PharmacyProfileDTO;
import com.tim40.tim40.dto.PharmacyRatingDTO;
import com.tim40.tim40.dto.PurchaseOrderDTO;
import com.tim40.tim40.dto.RejectedAcceptedDTO;
import com.tim40.tim40.model.Absence;
import com.tim40.tim40.model.Medication;
import com.tim40.tim40.model.Offer;
import com.tim40.tim40.model.Pharmacy;
import com.tim40.tim40.model.PurchaseOrder;
import com.tim40.tim40.model.QuantityMedication;
import com.tim40.tim40.service.PharmacyService;
@EnableTransactionManagement
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
	//ne menjati
	@PostMapping(value="purchaseorder-create-new-medication/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public boolean CreatePurchaseOrderForNewMedication(@PathVariable(name="id") String stringId,@RequestBody PurchaseOrderDTO dto) {
		long id = Long.valueOf(stringId);
		Pharmacy pharmacy = this.pharmacyService.getById(id);	
		return this.pharmacyService.CreatePurchaseOrderForNewMedication(pharmacy,dto);
		
	}
	//ne menjati
	@GetMapping(value = "/get-purchase-order/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Set<PurchaseOrder> getAllPurchaseOrders(@PathVariable("id") Long id){
	 Set<PurchaseOrder> purchaseOrders = this.pharmacyService.getAllPurchaseOrders(id);
		return purchaseOrders;
	}
	
	
	//ne menjati
	@Transactional()
	@PostMapping(value="/delete-purchase-order/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public boolean deletePurchaseOrder(@RequestBody Long id,@PathVariable("id") Long pharmacyId) {
		return this.pharmacyService.deletePurchaseOrder(id, pharmacyId);
	}
	
	
	
	//ne menjati
	@PostMapping(value = "/accept-offer",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
@Transactional()
	 //ovde je transakcija jer sta ako se desi da se ne apdejtuje purchase order status?! ->nekonzistentno
	public List<Offer> acceptAndDeclineOffers(@RequestBody AcceptOfferDTO acceptOffer){
		List<Offer> offers = this.pharmacyService.acceptOffer(acceptOffer);
		return offers;
	}
	//ne menjati

	@RequestMapping(method = RequestMethod.POST, path = "/delete-medication/{id}")
	@ResponseStatus(HttpStatus.OK)
	@Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_UNCOMMITTED,rollbackFor = Exception.class)
	public boolean deleteMedication(@RequestBody Long medicationId,@PathVariable("id") Long id){
		 System.out.println(id + " " + "PHARMACY ID");
		Pharmacy pharmacy = this.pharmacyService.getById(id);
		boolean success = this.pharmacyService.deleteMedication(id,medicationId);
		return success;
	}
	
	//ne menjati , da bih sprecila LOST UPDATE -> optimistic lock(version u modelu) + read_uncommited da bi se videle promene
	@Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_UNCOMMITTED,rollbackFor = Exception.class) //standardni izolacioni nivo + optimisticko zakljucavanje
	@PostMapping(value = "/edit-medication/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean editMedication(@RequestBody MedicationQuantityDTO dto,@PathVariable("id") Long pharmacyId){
		return this.pharmacyService.editMedication(dto, pharmacyId);
	}
	

	@GetMapping(value="/get-all-unapproved-absences/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<AbsenceDetailedDTO>> getAbsencesForPharmacyId(@PathVariable("id") Long id,@RequestHeader("usertype") String type){
		if("PHARMACY_ADMINISTRATOR".equals(type)){
			return new ResponseEntity<Set<AbsenceDetailedDTO>>(this.pharmacyService.getAllUnapprovedAbsencesByPharmacyId(id), HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Set<AbsenceDetailedDTO>>(HttpStatus.UNAUTHORIZED);
			}
	}
	
	@GetMapping(value="/get-all-approved-absences/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<Absence>> getApprovedAbsencesForPharmacyId(@PathVariable("id") Long id,@RequestHeader("usertype") String type){
		if("PHARMACY_ADMINISTRATOR".equals(type)){
			return new ResponseEntity<Set<Absence>>(this.pharmacyService.getAllApprovedAbsencesByPharmacyId(id), HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Set<Absence>>(HttpStatus.UNAUTHORIZED);
			}
	}
	

	@RequestMapping(value="/get-pharmacy-rating/{id}", method = RequestMethod.GET)
	public  ResponseEntity<List<PharmacyRatingDTO>> GetPharmacyRatings(@PathVariable("id") Long id ,@RequestHeader("usertype") String type){
		if("PHARMACY_ADMINISTRATOR".equals(type)){
			return new ResponseEntity<List<PharmacyRatingDTO>>( this.pharmacyService.getPharmacyRatings(id),HttpStatus.OK);
			}
			else {
				return new ResponseEntity<List<PharmacyRatingDTO>>(HttpStatus.UNAUTHORIZED);
			}
		
	}
	
	@RequestMapping(value="/is-medication-reserved/{id}", method = RequestMethod.POST)
	public  ResponseEntity<?> GetPharmacyRatings(@RequestBody Long medicationId,@PathVariable("id") Long id,@RequestHeader("usertype") String type){
		if("PHARMACY_ADMINISTRATOR".equals(type)){
			return new ResponseEntity<>( this.pharmacyService.isReserved(id, medicationId),HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		
	}

	@RequestMapping(value="/subscribe/{id}", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<Integer> subscribe(@RequestBody Long userId,@PathVariable("id") Long id,@RequestHeader("usertype") String type){
		if("PATIENT".equals(type)){
			return new ResponseEntity<Integer>( this.pharmacyService.subscribe(userId,id),HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Integer>(HttpStatus.UNAUTHORIZED);
			}
		
	}

	

}
