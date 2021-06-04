package com.tim40.tim40.model;

import javax.persistence.*;

@Embeddable
public class Address {
	
	@Column(name="state", nullable = false)
	private String state;
	
	@Column(name = "city", nullable = false)
	private String city;
	
	@Column(name = "street", nullable = false)
	private String street;
	
	@Column(name = "number", nullable = false)
	private String number;
	
	@Column(name = "postalcode", nullable = false)
	private int postalCode;

	public Address() {
		super();
	}

	public Address(String state, String city, String street, String number, int postalCode) {
		
		this.state = state;
		this.city = city;
		this.street = street;
		this.number = number;
		this.postalCode = postalCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
	
}
