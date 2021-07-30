package com.tim40.tim40.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tim40.tim40.model.Dermatologist;
import com.tim40.tim40.model.Patient;
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

	@Override
	public ResponseEntity<List<Dermatologist>> getAllDermatologists(Long ID) {
	   List<Dermatologist> dermatologist = dermatologistRepository.getAllByPharmacyId(ID);
	   return new ResponseEntity<List<Dermatologist>>(dermatologist, HttpStatus.OK);
		
	}
	
}
