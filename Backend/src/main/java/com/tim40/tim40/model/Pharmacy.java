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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pharmacies")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Pharmacy {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Embedded
    private Address address;

    @Column(name = "avg_rating", nullable = false)
    private double avgRating;
    @ManyToMany
    @JoinTable
    (
        name = "dermatologist_pharmacy",
        joinColumns = @JoinColumn(name = "pharmacy_id"),
        inverseJoinColumns = @JoinColumn(name = "dermatologist_id")
    )
    private final Set<Dermatologist> dermatologists = new HashSet<Dermatologist>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pharmacy")
    private final Set<Pharmacist> pharmacists = new HashSet<Pharmacist>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pharmacy")
    private final Set<Consultation> consultations = new HashSet<Consultation>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pharmacy")
    private final Set<Appointment> appointments = new HashSet<Appointment>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pharmacy")
    private final Set<Medication> medications = new HashSet<Medication>();
		@OneToMany(fetch = FetchType.LAZY,mappedBy="pharmacy")
		private final Set<PharmacyAdministrator> pharmacyAdministrators = new HashSet<PharmacyAdministrator>();
	
		@ManyToMany
	    @JoinTable
	    (
	        name = "pharmacy_suppliers",
	        joinColumns = @JoinColumn(name = "pharmacy_id"),
	        inverseJoinColumns = @JoinColumn(name = "supplier_id")
	    )
		private final Set<Supplier> suppliers = new HashSet<Supplier>();
		
    public Pharmacy() {}


    public Pharmacy(String name, Address address)
    {
        this.name = name;
        this.address = address;
        this.avgRating = 0.0;
    }

    public Pharmacy(Long id, String name, Address address)
    {
        this.id = id;
        this.address = address;
        this.name = name;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public double getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}

	public Set<Dermatologist> getDermatologists() {
		return dermatologists;
	}

	public Set<Pharmacist> getPharmacists() {
		return pharmacists;
	}

	public Set<Consultation> getConsultations() {
		return consultations;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public Set<Medication> getMedications() {
		return medications;
	}


	public Set<PharmacyAdministrator> getPharmacyAdministrators() {
		return pharmacyAdministrators;
	}

}
