package com.tim40.tim40.dto;

import java.util.HashSet;
import java.util.Set;

import com.tim40.tim40.model.Medication;
import com.tim40.tim40.model.enums.MedicationForm;
import com.tim40.tim40.model.enums.MedicinePublishingType;

public class MedicationDTO {

	private Long id; 
    private String name;
    private String code;
    private String typeOfMedicine;		
    private String structure;
    private String contraindications;
    private String recommendedIntake; 
    private MedicationForm medicationForm; 
    private String producer;
    private Long pharmacyID;
    private MedicinePublishingType publishingType;
    private String notes; 
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
    	this.medicationForm = medication.getMedicationForm();
    	this.producer = medication.getProducer();
    	this.pharmacyID = medication.getPharmacy().getId();
    	this.publishingType = medication.getPublishingType();
    	this.notes = medication.getNotes();
    	
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

	public MedicationForm getMedicationForm() {
		return medicationForm;
	}

	public void setMedicationForm(MedicationForm medicationForm) {
		this.medicationForm = medicationForm;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public MedicinePublishingType getPublishingType() {
		return publishingType;
	}

	public void setPublishingType(MedicinePublishingType publishingType) {
		this.publishingType = publishingType;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
