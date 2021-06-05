package com.tim40.tim40.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tim40.tim40.model.Medication;

public interface IMedicationService {
	ResponseEntity<List<Medication>> getReplacementMedication(Long pharmacyId, Long medicationId);
}
