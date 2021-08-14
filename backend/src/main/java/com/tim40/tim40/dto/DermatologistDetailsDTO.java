package com.tim40.tim40.dto;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tim40.tim40.model.Pharmacy;

//NE MENJATI NISTA OVDE KORISTI SE ZA FUNKCIONALNOST
public class DermatologistDetailsDTO {
	private Long id;
	private String name;
	private String surname;
	private String email;
	private Set<Pharmacy> pharmacyNames;
	private List<String> names;

	@JsonCreator
	public DermatologistDetailsDTO(Long id,String name,String surname, String email, Set<Pharmacy> pharmacyNames,List<String> names) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.pharmacyNames = pharmacyNames;
		this.names = names;
	}
	public DermatologistDetailsDTO() {
		
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<Pharmacy> getPharmacyNames() {
		return pharmacyNames;
	}

	public void setPharmacyNames(Set<Pharmacy> set) {
		this.pharmacyNames = set;
	}
	public List<String> getNames() {
		return names;
	}
	public void setNames(List<String> names) {
		this.names = names;
	}

}
