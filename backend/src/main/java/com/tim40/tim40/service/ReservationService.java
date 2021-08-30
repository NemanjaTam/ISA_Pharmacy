package com.tim40.tim40.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tim40.tim40.model.Appointment;
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

	@Override
	public Map<String, Integer> getMedicationYearlyPurchase(Long id) {
		List<Reservation> doneReservations = getDoneReservations(id);
		LocalDateTime now = LocalDateTime.now();
		int value = 1;
		Map<String, Integer> map = new HashMap<>();
		LocalDateTime yearBefore = LocalDateTime.of(now.getYear() - 1, now.getMonthValue(), now.getDayOfMonth(), 0, 0);
		System.out.println(yearBefore);
	    for (Reservation reservation : doneReservations) {
	    	System.out.println(reservation.getPeriod().getEndTime() + "VREME");
			if(reservation.getPeriod().getEndTime().isAfter(yearBefore) && reservation.getPeriod().getEndTime().isBefore(now)) {
				if(map.containsKey(reservation.getPeriod().getEndTime().getMonth().toString())) {
					map.put(reservation.getPeriod().getEndTime().getMonth().toString(),map.get(reservation.getPeriod().getEndTime().getMonth().toString()) + 1);
					
				}else {
					map.put(reservation.getPeriod().getEndTime().getMonth().toString(),value);
				}
		
				
			}
		}
	
		return map;
	}

	@Override
	public Map<String, Integer> getMedicationQuartalPurchase(Long id) {
		List<Reservation> doneReservations = getDoneReservations(id);
		LocalDateTime now = LocalDateTime.now();
		int value = 1;
		Map<String, Integer> map = new HashMap<>();
		LocalDateTime yearBefore = LocalDateTime.of(now.getYear(), now.getMonthValue() - 4, now.getDayOfMonth(), 0, 0);
		System.out.println(yearBefore);
	    for (Reservation reservation : doneReservations) {
	    	System.out.println(reservation.getPeriod().getEndTime() + "VREME");
			if(reservation.getPeriod().getEndTime().isAfter(yearBefore) && reservation.getPeriod().getEndTime().isBefore(now)) {
				if(map.containsKey(reservation.getPeriod().getEndTime().getMonth().toString())) {
					map.put(reservation.getPeriod().getEndTime().getMonth().toString(),map.get(reservation.getPeriod().getEndTime().getMonth().toString()) + 1);
					
				}else {
					map.put(reservation.getPeriod().getEndTime().getMonth().toString(),value);
				}
		
				
			}
		}
	
		return map;
	}

	@Override
	public Map<String, Integer> getMedicationMonthlyPurchase(Long id) {
		List<Reservation> doneReservations = getDoneReservations(id);
		LocalDateTime now = LocalDateTime.now();
		int value = 1;
		Map<String, Integer> map = new HashMap<>();
		LocalDateTime yearBefore = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), 0, 0);
		System.out.println(yearBefore);
	    for (Reservation reservation : doneReservations) {
	    
	    	System.out.println(reservation.getPeriod().getEndTime() + "VREME");
			if(reservation.getPeriod().getEndTime().getMonth().equals(now.getMonth()) &&reservation.getPeriod().getEndTime().getYear() == now.getYear() ){
				System.out.println("USLO U IF OVDE");
				if(map.containsKey(reservation.getPeriod().getEndTime().getMonth().toString())) {
					map.put(reservation.getPeriod().getEndTime().getMonth().toString(),map.get(reservation.getPeriod().getEndTime().getMonth().toString()) + 1);
					
				}else {
					map.put(reservation.getPeriod().getEndTime().getMonth().toString(),value);
				}
		
				
			}
		}
	
		return map;
	}

	@Override
	public List<Reservation> getDoneReservations(Long id) {
			List<Reservation> reservations = this.reservationRepository.findAll();
			List<Reservation> doneReservations = new ArrayList<Reservation>();
			for (Reservation reservation : reservations) {
				if(reservation.isDone() && reservation.getPharmacy().getId().equals(id)) {
					doneReservations.add(reservation);
				}
			}
			return doneReservations;
	}
	
}
