package com.tim40.tim40.service;

import org.springframework.http.ResponseEntity;

public interface IPatientService {
	ResponseEntity<String> addPenalty(Long id);
}
