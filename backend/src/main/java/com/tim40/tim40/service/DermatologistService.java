package com.tim40.tim40.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim40.tim40.dto.DermatologistDTO;
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

	@Override
	public DermatologistDTO getDermatologistByEmail(String email) {
//		List<Dermatologist> dermatologists = dermatologistRepository.findAll();
//		for(Dermatologist dermatologist : dermatologists) {
//			if(dermatologist.getEmail().equals(email)) {
//				System.out.println(dermatologist.getPharmacies().get(0));
//				return new DermatologistDTO(dermatologist);
//			}
//		}
//		return null;
		
		Dermatologist dermatologist = dermatologistRepository.findByEmail(email).orElse(null);
		
		return new DermatologistDTO();
	}
	
}
