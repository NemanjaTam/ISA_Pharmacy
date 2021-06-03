package com.tim40.tim40.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim40.tim40.dto.MedicationDTO;
import com.tim40.tim40.model.Medication;
import com.tim40.tim40.model.Pharmacy;
import com.tim40.tim40.repository.MedicationRepository;
import com.tim40.tim40.repository.PharmacyRepository;

@Service
public class MedicationService implements IMedicationService{
	
	private MedicationRepository medicationRepository;
	private PharmacyRepository pharmacyRepository;
	
	@Autowired
	public MedicationService(MedicationRepository medicationRepository, PharmacyRepository pharmacyRepository) {
		this.medicationRepository = medicationRepository;
		this.pharmacyRepository = pharmacyRepository;
	}

	public MedicationDTO createMedication (MedicationDTO medicationDTO) {
		Pharmacy pharmacy = pharmacyRepository.findById(medicationDTO.getPharmacyID()).get();
		Set<Medication> replacementMedications = new HashSet<Medication>();
		for(Long med : medicationDTO.getReplacementMedicationsIDs()) {
			replacementMedications.add(this.medicationRepository.findById(med).get());
		}
		Medication medication = new Medication(medicationDTO.getName(), medicationDTO.getCode(), medicationDTO.getTypeOfMedication(),
				medicationDTO.getStructure(), medicationDTO.getContraindications(), medicationDTO.getRecommendedIntake(), 
				pharmacy, replacementMedications);
		Medication createdMedication = medicationRepository.save(medication);
		return new MedicationDTO(createdMedication);
	}
}
