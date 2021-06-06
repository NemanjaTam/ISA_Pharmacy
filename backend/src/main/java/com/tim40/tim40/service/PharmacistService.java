package com.tim40.tim40.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tim40.tim40.model.Patient;
import com.tim40.tim40.model.Pharmacist;
import com.tim40.tim40.repository.PharmacistRepository;

@Service
public class PharmacistService implements IPharmacistService {

	private PharmacistRepository pharmacistRepository;

	@Autowired
	public PharmacistService(PharmacistRepository pharmacistRepository) {
		this.pharmacistRepository = pharmacistRepository;
	}

	@Override
	public ResponseEntity<Set<Patient>> getAllPatients(Long id) {
		Pharmacist pharmacist = pharmacistRepository.findById(id).get();
		return new ResponseEntity<Set<Patient>>(pharmacist.getAllPatients(), HttpStatus.OK);
	}
	
}
