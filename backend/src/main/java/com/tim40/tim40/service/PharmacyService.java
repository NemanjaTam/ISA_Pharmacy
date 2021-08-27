package com.tim40.tim40.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tim40.tim40.dto.PharmacyDTO;
import com.tim40.tim40.model.Medication;
import com.tim40.tim40.model.Pharmacy;
import com.tim40.tim40.model.QuantityMedication;
import com.tim40.tim40.repository.MedicationRepository;
import com.tim40.tim40.repository.PharmacyRepository;

@Service
public class PharmacyService implements IPharmacyService {
	
	private PharmacyRepository pharmacyRepository;
	private MedicationRepository medicationRepository;
	
	@Autowired
	public PharmacyService(PharmacyRepository pharmacyRepository, MedicationRepository medicationRepository) {
		this.pharmacyRepository = pharmacyRepository;
		this.medicationRepository = medicationRepository;
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

	@Override
	public Pharmacy getById(Long pharmacyID) {
		return pharmacyRepository.getById(pharmacyID);
	}

	@Override
	public ResponseEntity<List<Pharmacy>> getMedicationByPharmacy(String ids) {
		
		String[] splitted = ids.split(",");
		List<Medication> medications = new ArrayList<Medication>();
		List<Pharmacy> pharmacies = pharmacyRepository.findAll();
		List<Pharmacy> result = new ArrayList<Pharmacy>();
		
		for(String s : splitted) {
			int s2 = Integer.parseInt(s);
			medications.add(medicationRepository.findById((long) s2).get()) ;
			
		}
		for(Pharmacy p : pharmacies) {
			
			boolean exists = true;
			
			for(Medication medication: medications) {
				
				if(!isMedicationAvaiable(p, medication.getId()))  {
					exists = false;
				}
				
			}
			
			if(exists) {
				result.add(p);
			}
		}
		
		
		return new ResponseEntity<List<Pharmacy>>(result, HttpStatus.OK);
		
		
	}
	
	private boolean isMedicationAvaiable(Pharmacy pharmacy, long medicamentId) {
		
		for(QuantityMedication qm : pharmacy.getMedicationQuantity()) {
			if(qm.getQuantity() > 0 && qm.getMedication().getId().equals(medicamentId))
			{
				return true;
			}
		}
		
		return false;
	}


}
