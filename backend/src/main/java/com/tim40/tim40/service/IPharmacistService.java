package com.tim40.tim40.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.tim40.tim40.dto.PharmacistRatingDTO;
import com.tim40.tim40.model.Patient;
import com.tim40.tim40.model.Pharmacist;
import com.tim40.tim40.projections.DermatologistProjection;
import com.tim40.tim40.projections.PharmacistDetailsProjection;
import com.tim40.tim40.projections.PharmacistProjection;

public interface IPharmacistService {
	ResponseEntity<Set<Patient>> getAllPatients(Long id);
	Long getPharmacy(Long id);
	ResponseEntity<List<PharmacistDetailsProjection>> getAllPharamcistsNEW(Long ID);
	ResponseEntity<List<PharmacistDetailsProjection>> getAllPharamcistsById(Long ID);
	ResponseEntity<List<PharmacistRatingDTO>> getAllPharamcists();
	ResponseEntity<List<PharmacistDetailsProjection>> getAllPharmacistData(Long ID);
	
}
