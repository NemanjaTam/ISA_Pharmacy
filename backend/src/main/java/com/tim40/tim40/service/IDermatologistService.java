package com.tim40.tim40.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.tim40.tim40.model.Dermatologist;
import com.tim40.tim40.model.Patient;


public interface IDermatologistService {
	ResponseEntity<Set<Patient>> getAllPatients(Long id);
	ResponseEntity<List<Dermatologist>> getAllDermatologists(Long ID);
}
