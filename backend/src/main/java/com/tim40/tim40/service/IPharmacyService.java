package com.tim40.tim40.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tim40.tim40.model.Medication;

public interface IPharmacyService {
	ResponseEntity<List<Medication>> getAllMedications(Long id);
	ResponseEntity<String> isMedicationAvailable(Long pharmacyId, Long medicationId);
}
