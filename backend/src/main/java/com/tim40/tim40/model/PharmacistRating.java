package com.tim40.tim40.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pharmacist_ratings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PharmacistRating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private  Patient patient;
	
	@ManyToOne
    @JoinColumn(name = "pharmacist_id", nullable = false)
    private  Pharmacist pharmacist;
	
	
    @Column
    private int rating;

}
