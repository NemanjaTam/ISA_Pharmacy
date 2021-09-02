package com.tim40.tim40.controller;

import java.util.Map;

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

import com.tim40.tim40.dto.ReservationCheckDTO;
import com.tim40.tim40.model.Reservation;
import com.tim40.tim40.service.ReservationService;

@RestController
@RequestMapping(value = "api/reservation")
public class ReservationController {
	
	private ReservationService reservationService;

	@Autowired
	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	@PostMapping(value = "/get-reservations", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reservation> getReservations(@RequestBody ReservationCheckDTO reservationCheckDTO) throws Exception{
		return reservationService.getReservations(reservationCheckDTO.getInput(), reservationCheckDTO.getId());
	}
	
	@GetMapping(value="/get-report-for-year/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String,Integer>> getYearlyReportForMedication(@PathVariable("id") Long id,@RequestHeader("usertype") String type){
		if("PHARMACY_ADMINISTRATOR".equals(type)){
			return new ResponseEntity<Map<String,Integer>>(this.reservationService.getMedicationYearlyPurchase(id), HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Map<String,Integer>>(HttpStatus.UNAUTHORIZED);
			}
		
	}
	
	@GetMapping(value="/get-report-for-quartal/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String,Integer>> getQuartalReportForMedication(@PathVariable("id") Long id,@RequestHeader("usertype") String type){
		if("PHARMACY_ADMINISTRATOR".equals(type)){
			return new ResponseEntity<Map<String,Integer>>(this.reservationService.getMedicationQuartalPurchase(id), HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Map<String,Integer>>(HttpStatus.UNAUTHORIZED);
			}
		
	}
	
	@GetMapping(value="/get-report-for-month/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String,Integer>> getMonthlyLReportForMedication(@PathVariable("id") Long id,@RequestHeader("usertype") String type){
		if("PHARMACY_ADMINISTRATOR".equals(type)){
			return new ResponseEntity<Map<String,Integer>>(this.reservationService.getMedicationMonthlyPurchase(id), HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Map<String,Integer>>(HttpStatus.UNAUTHORIZED);
			}
		
	}
	
}
