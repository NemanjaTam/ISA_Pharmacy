package com.tim40.tim40.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tim40.tim40.dto.FilterMedicationDTO;
import com.tim40.tim40.dto.SearchMedicationDTO;
import com.tim40.tim40.model.Medication;

public interface IMedicationService {
	ResponseEntity<List<Medication>> getReplacementMedication(Long pharmacyId, Long medicationId);
	ResponseEntity<List<FilterMedicationDTO>> searchMedications(SearchMedicationDTO searchMedicationDTO);
}
