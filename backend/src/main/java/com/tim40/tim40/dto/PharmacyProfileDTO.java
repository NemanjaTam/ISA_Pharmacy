package com.tim40.tim40.dto;

import com.tim40.tim40.model.Address;

public class PharmacyProfileDTO {

	private String name;
    private Address address;
    private double avgRating;
    
	public PharmacyProfileDTO() {
		super();
	}

	public PharmacyProfileDTO(String name, Address address, double avgRating) {
		super();
		this.name = name;
		this.address = address;

		this.avgRating = avgRating;
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
	
}
