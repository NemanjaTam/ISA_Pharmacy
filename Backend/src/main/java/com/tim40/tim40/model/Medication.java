package com.tim40.tim40.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tim40.tim40.model.enums.MedicationForm;
import com.tim40.tim40.model.enums.MedicinePublishingType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "medications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Medication {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    @Column(nullable = false)
    private String name;
    @Column (nullable = false)
    private String code;
    @Column (nullable = false)
    private String typeOfMedicine;		// antibiotik, anestetik, antihistaminik..
    @Column(nullable = false) 
    private String structure;
    @Column (nullable = false)
    private String contraindications;
    @Column (nullable = false)
    @Enumerated(EnumType.STRING)
    private MedicationForm medicationForm;   // prasak, kapsula, gel..
    @Column (nullable = false)
    private String producer;   	
    @Column (nullable = false)
    @Enumerated(EnumType.STRING)
    private MedicinePublishingType publishingType;  	// sa receptom ili bez
    @Column (nullable = false)
    private String recommendedIntake;  // preporučeni unos terapije na dnevnom nivou
    @Column (nullable = false)
    private String notes; 			//dodatne napomene 
    @ManyToOne
    @JoinColumn(name = "pharmacy_id", nullable = false)
    private Pharmacy pharmacy;
    @ManyToMany(mappedBy = "allergedOnMedications")
    private final Set<Patient> patients = new HashSet<Patient>();
    @ManyToMany
    @JoinTable(
    		name = "replacement_medications",
            joinColumns = @JoinColumn(name = "medication_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "replacement_medication_id", referencedColumnName = "id")
    )
	private Set<Medication> replacementMedications = new HashSet<Medication>();
   
    public Medication (Long id, String name, String code, String typeOfMedicine, String structure, String contraindications, MedicationForm medicationForm,
    		MedicinePublishingType publishingType, String producer, String recommendedIntake, Pharmacy pharmacy, String notes,
    		Set<Medication> replacementMedications)
    {
    	super();
    	this.id = id;
    	this.name = name;
    	this.code = code;
    	this.typeOfMedicine = typeOfMedicine;
    	this.structure = structure;
    	this.contraindications = contraindications;
    	this.medicationForm = medicationForm;
    	this.publishingType = publishingType;
    	this.producer = producer;
    	this.recommendedIntake = recommendedIntake;
    	this.pharmacy = pharmacy; 
    	this.notes = notes;
    	this.replacementMedications = replacementMedications;
    }
    
    public Medication (String name, String code, String typeOfMedicine, String structure, String contraindications, MedicationForm medicationForm,
    		MedicinePublishingType publishingType, String producer, String recommendedIntake, Pharmacy pharmacy, String notes,
    		Set<Medication> replacementMedications)
    {
    	super();
    	this.name = name;
    	this.code = code;
    	this.typeOfMedicine = typeOfMedicine;
    	this.structure = structure;
    	this.contraindications = contraindications;
    	this.medicationForm = medicationForm;
    	this.publishingType = publishingType;
    	this.producer = producer;
    	this.recommendedIntake = recommendedIntake;
    	this.pharmacy = pharmacy; 
    	this.notes = notes;
    	this.replacementMedications = replacementMedications;
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

	public Set<Patient> getPatients() {
		return patients;
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
