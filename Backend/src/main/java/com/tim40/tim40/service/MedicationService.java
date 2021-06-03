package com.tim40.tim40.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
		Pharmacy pharmacy = pharmacyRepository.getOne(medicationDTO.getPharmacyID());
		Set<Medication> replacementMedications = new HashSet<Medication>();
		for(Long med : medicationDTO.getReplacementMedicationsIDs()) {
			replacementMedications.add(this.medicationRepository.getOne(med));
		}
		Medication medication = new Medication(medicationDTO.getName(), medicationDTO.getCode(), medicationDTO.getTypeOfMedicine(),
				medicationDTO.getStructure(), medicationDTO.getContraindications(), medicationDTO.getMedicationForm(), 
				medicationDTO.getPublishingType(), medicationDTO.getProducer(),  medicationDTO.getRecommendedIntake(), 
				pharmacy, medicationDTO.getNotes(), replacementMedications);
		Medication createdMedication = medicationRepository.save(medication);
		return new MedicationDTO(createdMedication);
	}
	
	public List<MedicationDTO> getAllMedications() {
		List<Medication> medications = medicationRepository.findAll();
		List<MedicationDTO> medicationDTOs = new ArrayList<MedicationDTO>();
		for(Medication m : medications) {
			medicationDTOs.add(new MedicationDTO(m));
		}
		return medicationDTOs;
	}
	
}
