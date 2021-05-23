package com.tim40.tim40.dto;

import java.util.HashSet;
import java.util.Set;

import com.tim40.tim40.model.Medication;

public class MedicationDTO {

	private Long id; 
    private String name;
    private String code;
    private String typeOfMedicine;		
    private String structure;
    private String contraindications;
    private String recommendedIntake; 
    private Long pharmacyID;
    private Set<Long> replacementMedicationsIDs = new HashSet<Long>();
 
  // private final Set<Patient> patients = new HashSet<Patient>();

    public MedicationDTO() { }
    
    public MedicationDTO (Medication medication) {
    	this.id = medication.getId();
    	this.name = medication.getName();
    	this.code = medication.getCode();
    	this.typeOfMedicine = medication.getTypeOfMedicine();
    	this.structure = medication.getStructure();
    	this.contraindications = medication.getContraindications();
    	this.recommendedIntake = medication.getRecommendedIntake();
    	this.pharmacyID = medication.getPharmacy().getId();
    	
    	for(Medication m : medication.getReplacementMedications()) {
    		this.replacementMedicationsIDs.add(m.getId());
    	}
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTypeOfMedicine() {
		return typeOfMedicine;
	}

	public void setTypeOfMedicine(String typeOfMedicine) {
		this.typeOfMedicine = typeOfMedicine;
	}

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public String getContraindications() {
		return contraindications;
	}

	public void setContraindications(String contraindications) {
		this.contraindications = contraindications;
	}

	public String getRecommendedIntake() {
		return recommendedIntake;
	}

	public void setRecommendedIntake(String recommendedIntake) {
		this.recommendedIntake = recommendedIntake;
	}


	public Long getPharmacyID() {
		return pharmacyID;
	}

	public void setPharmacyID(Long pharmacyID) {
		this.pharmacyID = pharmacyID;
	}

	public Set<Long> getReplacementMedicationsIDs() {
		return replacementMedicationsIDs;
	}

	public void setReplacementMedicationsIDs(Set<Long> replacementMedicationsIDs) {
		this.replacementMedicationsIDs = replacementMedicationsIDs;
	}
}
