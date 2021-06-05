package com.tim40.tim40.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "report", nullable = true)
	private String report;
	
	@Embedded
	private Period period;
	
	@Column
	private boolean isTaken;
	
	@Column(nullable = false)
	private boolean isFinished; 
	
	@ManyToOne
    @JoinColumn(name = "pharmacy_id", nullable = false)
    private Pharmacy pharmacy;
	
    @ManyToOne
    @JoinColumn(name = "dermatologist_id", nullable = false)
    private  Dermatologist dermatologist;
    
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    
    @OneToMany(fetch = FetchType.LAZY)
    private Set<Therapy> therapies = new HashSet<Therapy>();
    
}
