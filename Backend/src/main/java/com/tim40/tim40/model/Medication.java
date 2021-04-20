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
    @Column(nullable = false) 
    private String description;
    @Column(nullable = false)
    private int quantity; 
    @Column(nullable = false)
    private double price;
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
   
}
