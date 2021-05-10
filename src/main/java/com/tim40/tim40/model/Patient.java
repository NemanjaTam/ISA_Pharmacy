package com.tim40.tim40.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("Patient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends User {
	
	@Column(nullable = true)
	private Long penalties;
	
	@OneToMany(mappedBy = "patient")
	private Set<Consultation> consultations = new HashSet<Consultation>();
	
	@OneToMany(mappedBy = "patient")
	private Set<Appointment> appointments = new HashSet<Appointment>();
	
	@ManyToMany
    @JoinTable
    (
        name = "patient_allerged_on_medications",
        joinColumns = @JoinColumn(name = "patient_id"),
        inverseJoinColumns = @JoinColumn(name = "medication_id")
    )
	private Set<Medication> allergedOnMedications = new HashSet<Medication>();
}