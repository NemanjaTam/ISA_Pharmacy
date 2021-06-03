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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "eprescription")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Eprescription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Embedded
	private Period period;
	
	@Column(nullable = false)
	private boolean isUsed;
	
	@OneToMany(fetch = FetchType.LAZY)
	Set<QuantityMedication> prescribedMedications = new HashSet<QuantityMedication>();

	@ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private  Patient patient;
	
	@OneToOne
	private Pharmacy pharmacy;
}
