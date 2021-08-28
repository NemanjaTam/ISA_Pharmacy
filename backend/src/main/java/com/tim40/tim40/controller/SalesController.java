package com.tim40.tim40.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim40.tim40.dto.SaleDTO;
import com.tim40.tim40.dto.UserDTO;
import com.tim40.tim40.service.SaleService;
import com.tim40.tim40.service.UserService;

//ovo su zapravo akcije i sve to

@RestController
@RequestMapping(value = "api/sales")
public class SalesController {
	private SaleService saleService;

	@Autowired
	public SalesController(SaleService saleService) {
		this.saleService = saleService;
	}

	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE,consumes={"application/json"})
	public ResponseEntity<String> create(@RequestBody SaleDTO saleDTO) throws Exception
	{
		ResponseEntity<String> response = this.saleService.createSale(saleDTO.getForm(),Long. valueOf(saleDTO.getAdminId()) , saleDTO.getStartTime(), saleDTO.getEndTime(),true);
		
//		return new ResponseEntity<String>(registeredUser, HttpStatus.OK);
		return response;
	}
}
