package com.tim40.tim40.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tim40.tim40.dto.MedicationDTO;
import com.tim40.tim40.dto.MedicationDTO2;
import com.tim40.tim40.dto.MedicationQuantityDTO;
import com.tim40.tim40.model.Medication;
import com.tim40.tim40.model.Pharmacy;
import com.tim40.tim40.model.QuantityMedication;
import com.tim40.tim40.model.enums.MedicationForm;
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

	public MedicationDTO2 createMedication (MedicationDTO2 medicationDTO2) {
		Pharmacy pharmacy = pharmacyRepository.findById(medicationDTO2.getPharmacyID()).get();
		List<Medication> replacementMedications = new ArrayList<Medication>();
		for(Long med : medicationDTO2.getReplacementMedicationsIDs()) {
			replacementMedications.add(this.medicationRepository.findById(med).get());
		}
		Medication medication = new Medication(medicationDTO2.getName(), medicationDTO2.getCode(), medicationDTO2.getTypeOfMedication(),
				 medicationDTO2.getStructure(), medicationDTO2.getContraindications(), medicationDTO2.getRecommendedIntake(), medicationDTO2.getDescription(), 
				 medicationDTO2.getManufacturer(), medicationDTO2.getMedicationForm(), medicationDTO2.getPrescriptionRegime(), replacementMedications);
		Medication createdMedication = medicationRepository.save(medication);
		return new MedicationDTO2(createdMedication);
	}
	//ne menjati
	public Medication createMedicationWithoutReplacement (MedicationQuantityDTO medicationDTO) {
		
		Set<Medication> replacementMedications = new HashSet<Medication>();

		Medication medication = new Medication(medicationDTO.getName(),medicationDTO.getCode(),medicationDTO.getTypeOfMedication(),medicationDTO.getStructure(),medicationDTO.getContraindications(),medicationDTO.getRecommendedIntake(),medicationDTO.getDescription(),medicationDTO.getManufacturer()
				,medicationDTO.getMedicationForm(),medicationDTO.getPrescriptionRegime());
		Medication createdMedication = medicationRepository.save(medication);
		return createdMedication;
	}


	@Override
	public ResponseEntity<List<Medication>> getReplacementMedication(Long pharmacyId, Long medicationId) {
		Medication medication = medicationRepository.findById(medicationId).get();
		List<Medication> inPharmacy = new ArrayList<Medication>();
		Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId).get();
		for(Medication m : medication.getReplacementMedications()) {
			for(QuantityMedication qm : pharmacy.getMedicationQuantity()) {
				if(qm.getMedication().getId() == m.getId()) {
					inPharmacy.add(m);
				}
			}
		}
		return new ResponseEntity<List<Medication>>(medication.getReplacementMedications(), HttpStatus.OK);
	}
}
