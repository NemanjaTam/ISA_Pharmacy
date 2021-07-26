package com.tim40.tim40.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tim40.tim40.model.Pharmacist;
import com.tim40.tim40.model.Reservation;
import com.tim40.tim40.repository.PharmacistRepository;
import com.tim40.tim40.repository.ReservationRepository;

@Service
public class ReservationService implements IReservationService {

	private ReservationRepository reservationRepository;
	private PharmacistRepository pharmacistRepository;

	@Autowired
	public ReservationService(ReservationRepository reservationRepository, PharmacistRepository pharmacistRepository) {
		this.reservationRepository = reservationRepository;
		this.pharmacistRepository = pharmacistRepository;
	}

	@Override
	public ResponseEntity<Reservation> getReservations(String input, Long id) {
		List<Reservation> reservations = new ArrayList<Reservation>();
		Pharmacist pharmacist = pharmacistRepository.findById(id).get();
		for(Reservation r : reservationRepository.findAll()) {
			if(!r.isDone() && r.getPharmacy().getId() == pharmacist.getPharmacy().getId()) reservations.add(r);
		}
		for(Reservation r : reservations) {
			if(r.getSerialnumber().toLowerCase().equals(input.toLowerCase())) {
				LocalDate end = r.getPeriod().getEndTime().toLocalDate();
				LocalDate current = LocalDate.now();
				if(current.compareTo(end) <= 0) {
					return new ResponseEntity<Reservation>(r, HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<Reservation>(new Reservation(), HttpStatus.OK);
	}
	
}
