package com.tim40.tim40.service;

import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.tim40.tim40.model.Patient;


public interface IDermatologistService {
	ResponseEntity<Set<Patient>> getAllPatients(Long id);
}
