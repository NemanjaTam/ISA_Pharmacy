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
import com.tim40.tim40.model.Medication;
import com.tim40.tim40.model.Pharmacy;
import com.tim40.tim40.model.QuantityMedication;
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
		Medication medication = new Medication(medicationDTO.getName(), medicationDTO.getCode(),
				medicationDTO.getStructure(), medicationDTO.getContraindications(), medicationDTO.getRecommendedIntake(), 
				pharmacy, replacementMedications, medicationDTO.getNotes(), medicationDTO.getManufacturer(), medicationDTO.getMedicationForm(), medicationDTO.getTypeOfMedication());
		Medication createdMedication = medicationRepository.save(medication);
		return new MedicationDTO(createdMedication);
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
	
	public List<MedicationDTO> getAllMedications() {
		List<Medication> medications = medicationRepository.findAll();
		List<MedicationDTO> medicationDTOs = new ArrayList<MedicationDTO>();
		for(Medication m : medications) {
			medicationDTOs.add(new MedicationDTO(m));
		}
		return medicationDTOs; 
	}
}
