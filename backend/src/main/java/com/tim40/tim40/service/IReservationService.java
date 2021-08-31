package com.tim40.tim40.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.tim40.tim40.model.Reservation;

public interface IReservationService {
	ResponseEntity<Reservation> getReservations(String input, Long id);
	Map<String,Integer> getMedicationYearlyPurchase(Long id);
	Map<String,Integer> getMedicationQuartalPurchase(Long id);
	Map<String,Integer> getMedicationMonthlyPurchase(Long id);
    public List<Reservation> getDoneReservations(Long id);
}
