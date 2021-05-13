package com.tim40.tim40.model;

import java.util.List;

import javax.persistence.*;
@Entity
public class PriceList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
 
	@Embedded
	private Period durationPeriod;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "priceList")
	private List<Price> medicationPrices;
	
	//priceList se nalazi negde - verovatno u farmaciji ili negde drugde gde cu ih vezati
}
