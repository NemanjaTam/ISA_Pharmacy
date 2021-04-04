package com.tim40.tim40.dto;

import com.tim40.tim40.model.Address;
import com.tim40.tim40.model.User;
import com.tim40.tim40.model.enums.UserType;

public class UserDTO {
	private Long id;

	private String name;

	private String surname;
	
	private String email;

	private String password;

	private Address address;
	
	private UserType userType;
	
	public UserDTO() {
	}
	
	public UserDTO(User u) {
		this(u.getId(),u.getName(),u.getSurname(),u.getEmail(),u.getPassword(),u.getAddress(),u.getUserType());
	}
	
	public UserDTO(Long id, String name, String surname, String email, String password, Address address,
			UserType userType) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.address = address;
		this.userType = userType;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
}
