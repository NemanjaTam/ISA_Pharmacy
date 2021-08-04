package com.tim40.tim40.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tim40.tim40.model.Patient;
import com.tim40.tim40.model.Pharmacist;
import com.tim40.tim40.projections.PharmacistProjection;
import com.tim40.tim40.projections.PharmacyProjection;
import com.tim40.tim40.service.PharmacistService;
import com.tim40.tim40.service.PharmacyService;

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
	@RequestMapping(value="/getallpharmacistsbyid/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<PharmacistProjection>> getAllPharmacistByPharmacyId(@PathVariable("id") Long id){
		return pharmacistService.getAllPharamcists(id);	
	}
	
	//authorization: pharmacy admin & registered user
	@RequestMapping(value="/getallpharmacists/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<PharmacistProjection>> getAllPharmacist(@PathVariable("id") Long id,@RequestHeader("usertype") String type){
		if("PHARMACY_ADMINISTRATOR".equals(type)){
		return pharmacistService.getAllPharamcists(id);
		}
		else {
			System.out.println("NE VALJA");
			return new ResponseEntity<List<PharmacistProjection>>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	
	
	
	//moze samo pharmacy admin
	@RequestMapping(value="/getpharmacistdata/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<PharmacistProjection>> getPharmacistData(@PathVariable("id") Long id){
		
		return pharmacistService.getAllPharamcists(id);
	
	}

	
	
}
