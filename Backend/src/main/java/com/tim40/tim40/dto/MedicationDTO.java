package com.tim40.tim40.dto;

import java.util.HashSet;
import java.util.Set;
import com.tim40.tim40.model.Medication;
import com.tim40.tim40.model.Pharmacy;

public class MedicationDTO {

	private Long id; 
    private String name;
    private String code;
    private String typeOfMedicine;		
    private String structure;
    private String contraindications;
    private String recommendedIntake; 
    private Pharmacy pharmacy;
 
  // private final Set<Patient> patients = new HashSet<Patient>();
	private Set<Medication> replacementMedications = new HashSet<Medication>();
   
    public MedicationDTO() { }
    
    public MedicationDTO (Medication medication) {
    	this.id = medication.getId();
    	this.name = medication.getName();
    	this.code = medication.getCode();
    	this.typeOfMedicine = medication.getTypeOfMedicine();
    	this.structure = medication.getStructure();
    	this.contraindications = medication.getContraindications();
    	this.recommendedIntake = medication.getRecommendedIntake();
    	this.pharmacy = medication.getPharmacy();
    	this.replacementMedications = medication.getReplacementMedications();
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

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public Set<Medication> getReplacementMedications() {
		return replacementMedications;
	}

	public void setReplacementMedications(Set<Medication> replacementMedications) {
		this.replacementMedications = replacementMedications;
	}
}
