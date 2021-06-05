package com.tim40.tim40.dto;

import java.util.HashSet;

import java.util.Set;

import com.tim40.tim40.model.Medication;
import com.tim40.tim40.model.enums.TypeOfMedication;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicationDTO {

	private Long id; 
    private String name;
    private String code;
    private TypeOfMedication typeOfMedication;		
    private String structure;
    private String contraindications;
    private String recommendedIntake; 
    private Long pharmacyID;
    private Set<Long> replacementMedicationsIDs = new HashSet<Long>();
 
  // private final Set<Patient> patients = new HashSet<Patient>();
  
    public MedicationDTO (Medication medication) {
    	this.id = medication.getId();
    	this.name = medication.getName();
    	this.code = medication.getCode();
    	this.typeOfMedication = medication.getTypeOfMedication();
    	this.structure = medication.getStructure();
    	this.contraindications = medication.getContraindications();
    	this.recommendedIntake = medication.getRecommendedIntake();
//    	this.pharmacyID = medication.getPharmacy().getId();
    	
    	for(Medication m : medication.getReplacementMedications()) {
    		this.replacementMedicationsIDs.add(m.getId());
    	}
    }

	
}
