package com.tim40.tim40.controller;

import java.util.HashMap;

import java.util.List;

import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tim40.tim40.dto.DermatologistDTO;
import com.tim40.tim40.dto.DermatologistDetailsDTO;
import com.tim40.tim40.dto.DermatologistRatingDTO;
import com.tim40.tim40.model.*;
import com.tim40.tim40.projections.DermatologistDetailsProjection;
import com.tim40.tim40.projections.DermatologistProjection;
import com.tim40.tim40.projections.PharmacistDetailsProjection;
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
	//ne menjati, cyberpoetry17
	@RequestMapping(value="/getalldermatologist/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<DermatologistProjection>> getAllByPharmacyID(@PathVariable("id") Long id){
	 return dermatologistService.getAllDermatologists(id);
	}
	//ne menjati, cyberpoetry17
		@RequestMapping(value="/getalldermatologist2/{id}", method = RequestMethod.GET)
		public ResponseEntity<List<DermatologistDetailsProjection>> getAllByPharmacyID2(){
		 return dermatologistService.getAllDermatologistsForAllPharmacies();
		}
		//AUTORIZACIJA/AUTENTIKACIJA
		@RequestMapping(value="/getdermatologistsandpharmacynames/", method = RequestMethod.GET)
		public  ResponseEntity<List<DermatologistDetailsDTO>> GetDermatologistsAndPharmacyNames(@RequestHeader("usertype") String type){
			if("PHARMACY_ADMINISTRATOR".equals(type)){
				return dermatologistService.GetDermatologistsAndPharmacyNames();
				}
				else {
					return new ResponseEntity<List<DermatologistDetailsDTO>>(HttpStatus.UNAUTHORIZED);
				}
			
		}
		
		
		@RequestMapping(value="/get-dermatologist-rating/{id}", method = RequestMethod.GET)
		public  ResponseEntity<List<DermatologistRatingDTO>> GetDermatologistsRatings(@PathVariable("id") Long id ,@RequestHeader("usertype") String type){
			if("PHARMACY_ADMINISTRATOR".equals(type)){
				return new ResponseEntity<List<DermatologistRatingDTO>>( dermatologistService.getRatingsForDermatologists(id),HttpStatus.OK);
				}
				else {
					return new ResponseEntity<List<DermatologistRatingDTO>>(HttpStatus.UNAUTHORIZED);
				}
			
		}
		@Transactional()
		@RequestMapping(value="/remove-dermatologist/{id}", method = RequestMethod.POST)
		public  ResponseEntity<Integer> removeDermatologist(@PathVariable("id") Long id ,@RequestHeader("usertype") String type,@RequestBody String email){
			if("PHARMACY_ADMINISTRATOR".equals(type)){
				return new ResponseEntity<Integer>( dermatologistService.removeDermatologist(id,email),HttpStatus.OK);
				}
				else {
					return new ResponseEntity<Integer>(HttpStatus.UNAUTHORIZED);
				}
			
		}
		
		@RequestMapping(value="/get-by-email/{id}", method = RequestMethod.POST)
		public ResponseEntity<Long> getByEmail(@PathVariable("id") Long id ,@RequestHeader("usertype") String type,@RequestBody String email){
			if("PHARMACY_ADMINISTRATOR".equals(type)){
				return new ResponseEntity<Long>(this.dermatologistService.getIdByEmail(email, id),HttpStatus.OK);
				}
				else {
					return new ResponseEntity<Long>(HttpStatus.UNAUTHORIZED);
				}
		}
		
		
		
		
		
}
