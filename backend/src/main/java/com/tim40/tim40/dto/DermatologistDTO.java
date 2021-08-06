package com.tim40.tim40.dto;

import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DermatologistDTO {
	private Long id;
	private String name;
	private String surname;
	private String email;
	@JsonCreator
	public DermatologistDTO(@JsonProperty("id")Long id,@JsonProperty("name")String name,@JsonProperty("surname") String surname,@JsonProperty("email") String email) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
	}
	public DermatologistDTO() {
		
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

	
}
