package com.tim40.tim40.service;

import org.springframework.http.ResponseEntity;

import com.tim40.tim40.model.Reservation;

public interface IReservationService {
	ResponseEntity<Reservation> getReservations(String input, Long id);
}
