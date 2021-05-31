package com.tim40.tim40.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "eprescription")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Eprescription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Embedded
	private Period period;
	
	@Column(nullable = false)
	private boolean isUsed;
	
	@ManyToOne
    @JoinColumn(name = "medication_id", nullable = false)
    private  Medication medication;

	@ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private  Patient patient;
	
	@ManyToOne
    @JoinColumn(name = "pharmacy_id", nullable = false)
    private  Pharmacy pharmacy;
	
}
