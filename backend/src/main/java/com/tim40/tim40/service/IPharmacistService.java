package com.tim40.tim40.service;

import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.tim40.tim40.model.Patient;

public interface IPharmacistService {
	ResponseEntity<Set<Patient>> getAllPatients(Long id);
}
