package com.tim40.tim40.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim40.tim40.dto.PharmacyDTO;
import com.tim40.tim40.model.Pharmacist;
import com.tim40.tim40.model.Pharmacy;
import com.tim40.tim40.repository.PharmacyRepository;

@Service
public class PharmacyService implements IPharmacyService {
	
	private PharmacyRepository pharmacyRepository;

	@Autowired
	public PharmacyService(PharmacyRepository pharmacyRepository) {
		this.pharmacyRepository = pharmacyRepository;
	}
	
	public PharmacyDTO createPharmacy (PharmacyDTO pharmacyDTO) {
		Pharmacy pharmacy = new Pharmacy(pharmacyDTO.getName(), pharmacyDTO.getAddress());
		Pharmacy createdPharmacy = pharmacyRepository.save(pharmacy); 
		return new PharmacyDTO(createdPharmacy);
	}

	@Override
	public Pharmacy getPharmacyById(Long id) {
		Pharmacy pharmacy = pharmacyRepository.getById(id);
		//Pharmacist pharmacist = pharmacy.getPharmacists().get(0);
//		System.out.println(pharmacist.getUserType() + pharmacist.getId().toString());
		for(Pharmacist pharmacist : pharmacy.getPharmacists()) {
			System.out.println(pharmacist.getId().toString() + " - " + pharmacist.getUserType());
		}
		return pharmacy;
	}
	
}
