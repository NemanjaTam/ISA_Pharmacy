package com.tim40.tim40.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
}
