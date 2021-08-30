package com.tim40.tim40.dto;

import java.util.HashSet;
import java.util.Set;
import com.tim40.tim40.model.Medication;
import com.tim40.tim40.model.enums.MedicationForm;
import com.tim40.tim40.model.enums.PrescriptionRegime;
import com.tim40.tim40.model.enums.TypeOfMedication;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicationDTO2 {

	private Long id; 
    private String name;  
    private String code;	
    private TypeOfMedication typeOfMedication;	
    private MedicationForm medicationForm;   
    private String structure; 
    private String contraindications;  
    private String recommendedIntake;  
  //  private Long pharmacyID;
    private Set<Long> replacementMedicationsIDs = new HashSet<Long>();
    private String manufacturer; 
    private PrescriptionRegime prescriptionRegime;
    private String description;
    
  // private final Set<Patient> patients = new HashSet<Patient>();
  
    public MedicationDTO2 (Medication medication) {
    	this.id = medication.getId();
    	this.name = medication.getName();
    	this.code = medication.getCode();
    	this.typeOfMedication = medication.getTypeOfMedication();
    	this.medicationForm = medication.getMedicationForm();
    	this.structure = medication.getStructure();
    	this.contraindications = medication.getContraindications();
    	this.recommendedIntake = medication.getRecommendedIntake();
     //	this.pharmacyID = 
    	this.manufacturer = medication.getManufacturer();
    	this.prescriptionRegime = medication.getPrescriptionRegime();
    	this.description = medication.getDescription();
    	
    	for(Medication m : medication.getReplacementMedications()) {
    		this.replacementMedicationsIDs.add(m.getId());
    	}
    } 

	
}
