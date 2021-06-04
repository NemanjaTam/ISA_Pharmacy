package com.tim40.tim40.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tim40.tim40.dto.DermatologistDTO;
import com.tim40.tim40.service.DermatologistService;

@RestController
@RequestMapping(value = "api/dermatologist")
public class DermatologistController {
	
	private DermatologistService dermatologistService;

	@Autowired
	public DermatologistController(DermatologistService dermatologistService) {
		this.dermatologistService = dermatologistService;
	}
	
	@RequestMapping(value = "/get-dermatologist/{email}", method = RequestMethod.GET)
	public ResponseEntity<DermatologistDTO> getDermatologistByEmail(@PathVariable("email") String email) {
		DermatologistDTO dermatologistDTO = dermatologistService.getDermatologistByEmail(email);
		return new ResponseEntity<DermatologistDTO>(dermatologistDTO, HttpStatus.OK);
	}
	
}
