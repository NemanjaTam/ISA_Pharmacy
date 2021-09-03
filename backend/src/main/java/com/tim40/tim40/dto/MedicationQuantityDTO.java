package com.tim40.tim40.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tim40.tim40.model.Medication;
import com.tim40.tim40.model.MedicationRating;
import com.tim40.tim40.model.enums.MedicationForm;
import com.tim40.tim40.model.enums.PrescriptionRegime;
import com.tim40.tim40.model.enums.TypeOfMedication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicationQuantityDTO {

    private Long id; 
    private String name;
    private String description;
    private String manufacturer; 
    private MedicationForm medicationForm;
    private PrescriptionRegime prescriptionRegime;
    private String code;
    private TypeOfMedication typeOfMedication;		// antibiotik, anestetik, antihistaminik..
    private String structure; //ne znam sta je ovo?
    private String contraindications;
    private String recommendedIntake;  // preporučeni unos terapije na dnevnom nivou
    private Set<MedicationRating> ratings;
	private List<Medication> replacementMedications = new ArrayList<Medication>();
	private Long quantity;
	private Long version;
	
	
    
}
