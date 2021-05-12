package com.tim40.tim40.dto;

import com.tim40.tim40.model.Address;

public class AddressDTO {

	private String state;
	private String city;
	private String street;
	private String number;
	private int postalCode;
	
	public AddressDTO() {
		
	}
	
	public AddressDTO(Address a) {
		this(a.getState(),a.getCity(),a.getStreet(),a.getNumber(),a.getPostalCode());
	}
	
	public AddressDTO(String state, String city, String street, String number, int postalCode) {
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
