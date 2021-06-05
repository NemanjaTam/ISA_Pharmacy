package com.tim40.tim40.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tim40.tim40.model.Patient;
import com.tim40.tim40.repository.PatientRepository;

@Service
public class PatientService implements IPatientService {
	
	private PatientRepository patientRepository;

	@Autowired
	public PatientService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	@Override
	public ResponseEntity<String> addPenalty(Long id) {
		Patient patient = patientRepository.findById(id).get();
		Long current = patient.getPenalties();
		current = current + 1;
		patient.setPenalties(current);
		patientRepository.save(patient);
		return new ResponseEntity<String>("ok", HttpStatus.OK);
	}
	
}
