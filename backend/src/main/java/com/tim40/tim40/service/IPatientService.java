package com.tim40.tim40.service;

import org.springframework.http.ResponseEntity;

public interface IPatientService {
	ResponseEntity<String> addPenalty(Long id);
	ResponseEntity<String> isAlergedOnMedication(Long patientId, Long medicationId);
	ResponseEntity<Boolean> subscribeToPharmacy(Long patientId, Long pharmacyId);
	ResponseEntity<Boolean> unsubscribeToPharmacy(Long patientId, Long pharmacyId);
}
