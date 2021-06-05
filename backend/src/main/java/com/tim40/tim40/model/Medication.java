package com.tim40.tim40.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tim40.tim40.model.enums.MedicationForm;
import com.tim40.tim40.model.enums.PrescriptionRegime;
import com.tim40.tim40.model.enums.TypeOfMedication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "medications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medication {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String manufacturer; //moze biti entitet ali komplikujemo

	 @Column(name = "medication_form", nullable = false)
	 @Enumerated(EnumType.STRING)
    private MedicationForm medicationForm;

  	@Column(name = "prescription_regime", nullable = false)
    @Enumerated(EnumType.STRING)
    private PrescriptionRegime prescriptionRegime;

	@Column (nullable = false)
    private String code;
	
	@Column(name = "medication_type", nullable = false)
	@Enumerated(EnumType.STRING)
    private TypeOfMedication typeOfMedication;		// antibiotik, anestetik, antihistaminik..
	
    @Column(nullable = false)
    private String structure; //ne znam sta je ovo?
    
    @Column (nullable = false)
    private String contraindications;
    
    @Column (nullable = false)
    private String recommendedIntake;  // preporučeni unos terapije na dnevnom nivou

//    @ManyToOne
//    @JoinColumn(name = "pharmacy_id", nullable = false)
//    private Pharmacy pharmacy;

//    @ManyToMany(mappedBy = "allergedOnMedications")
//    private final Set<Patient> patients = new HashSet<Patient>();
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy="medication")
    private Set<MedicationRating> ratings;

    @JsonBackReference
    @ManyToMany
    @JoinTable(
    		name = "replacement_medications",
            joinColumns = @JoinColumn(name = "medication_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "replacement_medication_id", referencedColumnName = "id")
    )
	private Set<Medication> replacementMedications = new HashSet<Medication>();
    
    public Medication (String name, String code, TypeOfMedication typeOfMedication, String structure, String contraindications,
            String recommendedIntake, Pharmacy pharmacy, Set<Medication> replacementMedications)
    {
        super();
        this.name = name;
        this.code = code;
        this.typeOfMedication = typeOfMedication;
        this.structure = structure;
        this.contraindications = contraindications;
        this.recommendedIntake = recommendedIntake;
//        this.pharmacy = pharmacy; 
        this.replacementMedications = replacementMedications;
    }
}
