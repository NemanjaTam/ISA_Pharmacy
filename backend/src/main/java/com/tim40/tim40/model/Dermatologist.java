package com.tim40.tim40.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@DiscriminatorValue("Dermatologist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dermatologist extends User {
    @ManyToMany
	@JoinTable
	(
	    name = "dermatologist_pharmacy",
	    joinColumns = @JoinColumn(name = "dermatologist_id"),
	    inverseJoinColumns = @JoinColumn(name = "pharmacy_id")
	)
    private final Set<Pharmacy> pharmacies = new HashSet<Pharmacy>();
	
	@OneToMany(mappedBy = "dermatologist")
	private Set<Appointment> appointments = new HashSet<Appointment>();
	
//	@OneToMany(fetch = FetchType.LAZY,mappedBy="dermatologist")
//    private Set<DermatologistRating> ratings = new HashSet<DermatologistRating>();
	
    @ManyToMany
	@JoinTable
	(
	    name = "dermatologist_patient",
	    joinColumns = @JoinColumn(name = "dermatologist_id"),
	    inverseJoinColumns = @JoinColumn(name = "patient_id")
	)
	private Set<Patient> allPatients = new HashSet<Patient>();
	
}