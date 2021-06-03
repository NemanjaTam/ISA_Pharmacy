package com.tim40.tim40.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim40.tim40.repository.ReservationRepository;

@Service
public class ReservationService implements IReservationService {

	private ReservationRepository reservationRepository;

	@Autowired
	public ReservationService(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}
	
}
