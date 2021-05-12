package com.tim40.tim40.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim40.tim40.repository.MedicationRepository;

@Service
public class MedicationService implements IMedicationService{
	
	private MedicationRepository medicationRepository;

	@Autowired
	public MedicationService(MedicationRepository medicationRepository) {
		this.medicationRepository = medicationRepository;
	}

}
