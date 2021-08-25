package com.tim40.tim40.dto;

import java.util.HashSet;
import java.util.Set;

import com.tim40.tim40.model.Medication;
import com.tim40.tim40.model.enums.MedicationForm;
import com.tim40.tim40.model.enums.TypeOfMedication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicationDTO {

	private Long id; 
    private String name;
    private String code;
    private TypeOfMedication typeOfMedication;	
    private MedicationForm medicationForm;
    private String structure;
    private String contraindications;
    private String recommendedIntake; 
    private Long pharmacyID;
    private Set<Long> replacementMedicationsIDs = new HashSet<Long>();
    private String notes;
    private String manufacturer;
    private double avgRate;
    private String medicationSpecification;
    
  // private final Set<Patient> patients = new HashSet<Patient>();
  
    public MedicationDTO (Medication medication) {
    	this.id = medication.getId();
    	this.name = medication.getName();
    	this.code = medication.getCode();
    	this.typeOfMedication = medication.getTypeOfMedication();
    	this.structure = medication.getStructure();
    	this.contraindications = medication.getContraindications();
    	this.recommendedIntake = medication.getRecommendedIntake();
    	this.notes = medication.getNotes();
    	this.manufacturer = medication.getManufacturer();
    	this.medicationForm = medication.getMedicationForm();
//    	this.pharmacyID = medication.getPharmacy().getId();
    	
    	for(Medication m : medication.getReplacementMedications()) {
    		this.replacementMedicationsIDs.add(m.getId());
    	}
    	
    	String specification = "";
    	specification += "Contraindications: " + (medication.getContraindications().length() > 0 ? medication.getContraindications() : "none") + ".";
    	specification += "Structure: " + (medication.getStructure().length() > 0 ? medication.getStructure() : "none") + ".";
    	specification += "Recommended intake: " + (medication.getRecommendedIntake().length() > 0 ? medication.getRecommendedIntake() : "none") + ".";
    	specification += "Replacement medications: ";
    	
    	for(Medication m : medication.getReplacementMedications()) {
    		specification += m.getName() + ", ";
    	}
    	
    	this.medicationSpecification = specification.substring(0, specification.length()-1);
    }

	
}
