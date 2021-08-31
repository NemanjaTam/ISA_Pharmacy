package com.tim40.tim40.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("Pharmacist")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pharmacist extends User {
//	@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "pharmacy_id", nullable = true)
	@OneToOne
    private Pharmacy pharmacy;
	
//	@OneToMany(mappedBy = "pharmacist",fetch =FetchType.LAZY,cascade = CascadeType.ALL)
//	private Set<Consultation> consultations = new HashSet<Consultation>();
		
//	@OneToMany(fetch = FetchType.LAZY,mappedBy="pharmacist")
//    private Set<PharmacistRating> ratings = new HashSet<PharmacistRating>();
	
	@OneToMany(fetch = FetchType.LAZY)
	private Set<Patient> allPatients = new HashSet<Patient>();
}
