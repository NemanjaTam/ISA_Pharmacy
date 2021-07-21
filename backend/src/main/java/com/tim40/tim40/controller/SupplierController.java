package com.tim40.tim40.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim40.tim40.dto.PurchaseOrderOfferDTO;
import com.tim40.tim40.service.SupplierService;

@RestController
@RequestMapping(value = "api/supplier")
public class SupplierController {
	
	private SupplierService supplierService;

	@Autowired
	public SupplierController(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	@PostMapping(value = "/make-offer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PurchaseOrderOfferDTO> makePurchaseOrderOffer(@RequestBody PurchaseOrderOfferDTO purchaseOrderOfferDTO) throws Exception{
		return supplierService.makePurchaseOrderOffer(purchaseOrderOfferDTO);
	}
	
	@GetMapping(value = "/all-offers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PurchaseOrderOfferDTO>> getPurchaseOrderOffers(@PathVariable("id") Long id) throws Exception{
		return supplierService.getPurchaseOrderOffers(id);
	}
}
