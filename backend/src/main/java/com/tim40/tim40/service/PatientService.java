package com.tim40.tim40.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tim40.tim40.model.Medication;
import com.tim40.tim40.model.Patient;
import com.tim40.tim40.model.Pharmacy;
import com.tim40.tim40.repository.PatientRepository;
import com.tim40.tim40.repository.PharmacyRepository;

@Service
public class PatientService implements IPatientService {
	
	private PatientRepository patientRepository;
	private PharmacyRepository pharmacyRepository;
	
	@Autowired
	public PatientService(PatientRepository patientRepository, PharmacyRepository pharmacyRepository) {
		this.patientRepository = patientRepository;
		this.pharmacyRepository = pharmacyRepository;
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

	@Override
	public ResponseEntity<String> isAlergedOnMedication(Long patientId, Long medicationId) {
		Patient patient =patientRepository.findById(patientId).get();
		for(Medication m : patient.getAllergedOnMedications()) {
			if(m.getId() == medicationId) {
				return new ResponseEntity<String>("allerged", HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("not_allerged", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Boolean> subscribeToPharmacy(Long patientId, Long pharmacyId) {
		Patient patient = patientRepository.findById(patientId).get();
		Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId).get();
		
		if(patient == null || pharmacy == null) 
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		
		patient.getSubscribedToPharmacies().add(pharmacy);
		patientRepository.save(patient);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Boolean> unsubscribeToPharmacy(Long patientId, Long pharmacyId) {
		Patient patient = patientRepository.findById(patientId).get();
		Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId).get();
		
		if(patient == null || pharmacy == null) 
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		
		patient.getSubscribedToPharmacies().remove(pharmacy);
		patientRepository.save(patient);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
}
