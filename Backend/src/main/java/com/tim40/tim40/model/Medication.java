package com.tim40.tim40.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
    private String recommendedIntake;  // preporučeni unos terapije na dnevnom nivou
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
   
    public Medication() {
    	super();
    }
    
    public Medication (Long id, String name, String code, String typeOfMedicine, String structure, String contraindications,
    		String recommendedIntake, Pharmacy pharmacy, Set<Medication> replacementMedications)
    {
    	super();
    	this.id = id;
    	this.name = name;
    	this.code = code;
    	this.typeOfMedicine = typeOfMedicine;
    	this.structure = structure;
    	this.contraindications = contraindications;
    	this.recommendedIntake = recommendedIntake;
    	this.pharmacy = pharmacy; 
    	this.replacementMedications = replacementMedications;
    }
    
    public Medication (String name, String code, String typeOfMedicine, String structure, String contraindications,
    		String recommendedIntake, Pharmacy pharmacy, Set<Medication> replacementMedications)
    {
    	super();
    	this.name = name;
    	this.code = code;
    	this.typeOfMedicine = typeOfMedicine;
    	this.structure = structure;
    	this.contraindications = contraindications;
    	this.recommendedIntake = recommendedIntake;
    	this.pharmacy = pharmacy; 
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
}
