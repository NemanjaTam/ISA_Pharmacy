package com.tim40.tim40.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("LoyaltyProgram")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoyaltyProgram {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "naziv", nullable = false)
	private String naziv;
	@Column(name = "bodovi", nullable = false)
	private Integer bodovi;
	@Column(name = "popust", nullable = false)
	private double popust;
	

}
