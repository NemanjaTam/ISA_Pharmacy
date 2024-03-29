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

import com.tim40.tim40.dto.OfferDTO;
import com.tim40.tim40.dto.OfferFilterDTO;
import com.tim40.tim40.dto.PurchaseOrderOfferDTO;
import com.tim40.tim40.model.PurchaseOrder;
import com.tim40.tim40.model.QuantityMedication;
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

	@PostMapping(value = "/{id}/offers", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OfferDTO>> getSupplierOffers(@PathVariable("id") Long id, @RequestBody OfferFilterDTO offerFilterDTO) throws Exception{
		return supplierService.getSupplierOffers(id, offerFilterDTO);
	}
	
	@PostMapping(value = "/update-offer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PurchaseOrderOfferDTO> updatePurchaseOrderOffer(@RequestBody PurchaseOrderOfferDTO purchaseOrderOfferDTO) throws Exception{
		return supplierService.updatePurchaseOrderOffer(purchaseOrderOfferDTO);
	}
	
	@GetMapping(value = "/orders-on-hold", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PurchaseOrder>> getAllOrders(){
		return supplierService.getOrdersOnHold();
	}
	
	@GetMapping(value="/pairs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<QuantityMedication>> getPairs(@PathVariable("id") Long id){
		return supplierService.getPairs(id);
	}
	
	@PostMapping(value="/filtered-offers/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PurchaseOrderOfferDTO>> getPurchaseOrderOffersFiltered(@RequestBody List<String> optionsSelected, @PathVariable("id") Long id) throws Exception{
		return supplierService.getPurchaseOrderOffersFiltered(optionsSelected, id);
	}
}