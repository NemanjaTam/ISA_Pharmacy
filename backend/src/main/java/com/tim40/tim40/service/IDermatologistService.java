package com.tim40.tim40.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.tim40.tim40.dto.DermatologistDTO;
import com.tim40.tim40.model.Dermatologist;
import com.tim40.tim40.model.Patient;
import com.tim40.tim40.projections.DermatologistDetailsProjection;
import com.tim40.tim40.projections.DermatologistProjection;


public interface IDermatologistService {
	ResponseEntity<Set<Patient>> getAllPatients(Long id);
	ResponseEntity<List<DermatologistProjection>> getAllDermatologists(Long ID);
	ResponseEntity<List<DermatologistDetailsProjection>> getAllDermatologistsForAllPharmacies();

}
