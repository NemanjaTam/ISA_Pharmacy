package com.tim40.tim40.model;

import java.util.List;

import javax.persistence.*;
@Entity
public class PriceList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private boolean active;
 
	@Embedded
	private Period durationPeriod;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "priceList")
	private List<Price> medicationPrices;
	
	@ManyToOne
    @JoinColumn(name = "pharmacy_id", nullable = false)
	private Pharmacy pharmacy;

	public PriceList() {
		
	}

	public PriceList(Long id, boolean active, Period durationPeriod, List<Price> medicationPrices, Pharmacy pharmacy) {
		super();
		this.id = id;
		this.active = active;
		this.durationPeriod = durationPeriod;
		this.medicationPrices = medicationPrices;
		this.pharmacy = pharmacy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Period getDurationPeriod() {
		return durationPeriod;
	}

	public void setDurationPeriod(Period durationPeriod) {
		this.durationPeriod = durationPeriod;
	}

	public List<Price> getMedicationPrices() {
		return medicationPrices;
	}

	public void setMedicationPrices(List<Price> medicationPrices) {
		this.medicationPrices = medicationPrices;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	
}
