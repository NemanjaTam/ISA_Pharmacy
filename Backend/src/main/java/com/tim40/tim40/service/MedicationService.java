package com.tim40.tim40.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim40.tim40.dto.MedicationDTO;
import com.tim40.tim40.model.Medication;
import com.tim40.tim40.repository.MedicationRepository;

@Service
public class MedicationService implements IMedicationService{
	
	private MedicationRepository medicationRepository;

	@Autowired
	public MedicationService(MedicationRepository medicationRepository) {
		this.medicationRepository = medicationRepository;
	}

	public MedicationDTO createMedication (MedicationDTO medicationDTO) {
		Medication medication = new Medication(medicationDTO.getName(), medicationDTO.getCode(), medicationDTO.getTypeOfMedicine(),
				medicationDTO.getStructure(), medicationDTO.getContraindications(), medicationDTO.getRecommendedIntake(), 
				medicationDTO.getPharmacy(), medicationDTO.getReplacementMedications());
		Medication createdMedication = medicationRepository.save(medication);
		return new MedicationDTO(createdMedication);
	}
}
