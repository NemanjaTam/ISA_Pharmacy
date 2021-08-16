package com.tim40.tim40.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tim40.tim40.dto.PharmacyDTO;
import com.tim40.tim40.email.service.MailService;
import com.tim40.tim40.model.Dermatologist;
import com.tim40.tim40.model.Medication;
import com.tim40.tim40.model.Patient;
import com.tim40.tim40.model.Pharmacy;
import com.tim40.tim40.model.QuantityMedication;
import com.tim40.tim40.model.User;
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
	public ResponseEntity<List<Medication>> getAllMedications(Long id) {
		Pharmacy pharmacy = pharmacyRepository.findById(id).get();
		List<Medication> medications = new ArrayList<Medication>();
		for(QuantityMedication qm : pharmacy.getMedicationQuantity()) {
			medications.add(qm.getMedication());
		}
		return new ResponseEntity<List<Medication>>(medications,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> isMedicationAvailable(Long pharmacyId, Long medicationId) {
		Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId).get();
		for(QuantityMedication qm : pharmacy.getMedicationQuantity()) {
			if(qm.getQuantity() == 0) {
				return new ResponseEntity<String>("not_available", HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("available", HttpStatus.OK);
	}
//ne menjati
	@Override
	public Pharmacy getById(Long pharmacyID) {
		return pharmacyRepository.getById(pharmacyID);
	}
//ne menjati
	@Override
	public List<String> getAllSubscribers(Long pharmacyID) {
		Pharmacy pharmacy = this.pharmacyRepository.getById(pharmacyID);
		Set<User>subscribers = pharmacy.getSubscribers();
		List<String> emails = new ArrayList<String>();
		for(User sub: subscribers) {
			System.out.println(sub.getEmail());
			emails.add(sub.getEmail());
		}
		return emails;
	}
//ne menjati
	@Override
	public Long getPharmacyIdByUserId(Long id) {
		return this.pharmacyRepository.getPharmacyIdByUserId(id);
		
	}


	
}
