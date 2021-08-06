package com.tim40.tim40.service;

import java.math.BigInteger;
import java.util.ArrayList;

import java.util.List;
import java.util.Set;

import javax.persistence.Tuple;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tim40.tim40.dto.DermatologistDTO;
import com.tim40.tim40.model.Dermatologist;
import com.tim40.tim40.model.Patient;
import com.tim40.tim40.projections.DermatologistDetailsProjection;
import com.tim40.tim40.projections.DermatologistProjection;
import com.tim40.tim40.repository.DermatologistRepository;

@Service
public class DermatologistService implements IDermatologistService {

	private DermatologistRepository dermatologistRepository;

	@Autowired
	public DermatologistService(DermatologistRepository dermatologistRepository) {
		this.dermatologistRepository = dermatologistRepository;
	}

	@Override
	public ResponseEntity<Set<Patient>> getAllPatients(Long id) {
		Dermatologist dermatologist = dermatologistRepository.findById(id).get();
		return new ResponseEntity<Set<Patient>>(dermatologist.getAllPatients(), HttpStatus.OK);
	}

	//ne menjati
	@Override
	public ResponseEntity<List<DermatologistProjection>> getAllDermatologists(Long ID) {
	   List<DermatologistProjection> dermatologist = dermatologistRepository.getAllByPharmacyId(ID);
	   return new ResponseEntity<List<DermatologistProjection>>(dermatologist, HttpStatus.OK);
		
	}
	
	public ResponseEntity<List<DermatologistDetailsProjection>> getAllDermatologistsForAllPharmacies() {
		   List<DermatologistDetailsProjection> dermatologist = dermatologistRepository.getAllByPharmacyIdWithPharmacyList();
		   return new ResponseEntity<List<DermatologistDetailsProjection>>(dermatologist, HttpStatus.OK);
			
		}
	
}
