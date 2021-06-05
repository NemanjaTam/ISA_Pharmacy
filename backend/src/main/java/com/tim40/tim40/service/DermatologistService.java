package com.tim40.tim40.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim40.tim40.model.Dermatologist;
import com.tim40.tim40.model.Pharmacist;
import com.tim40.tim40.model.Pharmacy;
import com.tim40.tim40.repository.DermatologistRepository;

@Service
public class DermatologistService implements IDermatologistService {

	private DermatologistRepository dermatologistRepository;

	@Autowired
	public DermatologistService(DermatologistRepository dermatologistRepository) {
		this.dermatologistRepository = dermatologistRepository;
	}
	
}
