package com.tim40.tim40.dto;
//ne menjati koristi se
import com.tim40.tim40.model.Address;
import com.tim40.tim40.model.Pharmacy;

public class PharmacyDTO {

	private String name;
    private Address address;
    private Long id;
    private boolean subscribed;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private double avgRating;
  /*  private final Set<Dermatologist> dermatologists = new HashSet<Dermatologist>();
    private final Set<Pharmacist> pharmacists = new HashSet<Pharmacist>();
    private final Set<Consultation> consultations = new HashSet<Consultation>();
    private final Set<Appointment> appointments = new HashSet<Appointment>();
    private final Set<Medication> medications = new HashSet<Medication>(); */
    
    public PharmacyDTO() { }
    
    public PharmacyDTO (Pharmacy pharmacy) {
    	this.name = pharmacy.getName();
    	this.id = pharmacy.getId();
    	this.address = pharmacy.getAddress();
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

	public boolean isSubscribed() {
		return subscribed;
	}

	public void setSubscribed(boolean subscribed) {
		this.subscribed = subscribed;
	}
    
}
