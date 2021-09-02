package com.tim40.tim40.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.tim40.tim40.dto.PriceListMedicationDTO;
import com.tim40.tim40.model.Absence;
import com.tim40.tim40.model.PriceListMedication;
import com.tim40.tim40.service.PriceListService;

@RestController
@RequestMapping(value = "api/pricelist")
public class PriceListController {
	private PriceListService priceListService;
	

	@Autowired
	public PriceListController(PriceListService priceListService) {
		this.priceListService = priceListService;
	}


	@GetMapping(value="/get-active-pricelist/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PriceListMedication>> getActivePriceList(@PathVariable("id") Long id,@RequestHeader("usertype") String type){
			if(("PHARMACY_ADMINISTRATOR".equals(type) || ("PATIENT".equals(type)))){
				return new ResponseEntity<List<PriceListMedication>>(this.priceListService.getActivePriceList(id), HttpStatus.OK);
			
		
			}
			else {
				return new ResponseEntity<List<PriceListMedication>>(HttpStatus.UNAUTHORIZED);
			}
	}
	
	@GetMapping(value="/get-non-active-pricelist/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PriceListMedication>> getNonActivePriceList(@PathVariable("id") Long id,@RequestHeader("usertype") String type){
			if("PHARMACY_ADMINISTRATOR".equals(type)){
				return new ResponseEntity<List<PriceListMedication>>(this.priceListService.getNonActivePriceList(id), HttpStatus.OK);
			
		
			}
			else {
				return new ResponseEntity<List<PriceListMedication>>(HttpStatus.UNAUTHORIZED);
			}
	}
	
	
	
	
	@PostMapping(value="/edit/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PriceListMedication>> editPriceList(@RequestBody PriceListMedicationDTO plm,@RequestHeader("usertype") String type,@PathVariable("id") Long id){
			if("PHARMACY_ADMINISTRATOR".equals(type)){
				String timeToParse = plm.getStartTime();
				String[] tokens = timeToParse.split("-");
				String[] days = tokens[2].split("T");
				String[] timeparsing = days[1].split(":");
				LocalDate date = LocalDate.of(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(days[0]));
				
				LocalTime time = LocalTime.of(Integer.parseInt(timeparsing[0]), Integer.parseInt(timeparsing[1]));
				LocalDateTime newTime = LocalDateTime.of(date, LocalTime.now());
				System.out.println(newTime);
				return new ResponseEntity<List<PriceListMedication>>(this.priceListService.editPriceList(id, newTime, plm.getMedicationPrices(), id), HttpStatus.OK);
			
			
		
			}
			else {
				return new ResponseEntity<List<PriceListMedication>>(HttpStatus.UNAUTHORIZED);
			}
	}
}