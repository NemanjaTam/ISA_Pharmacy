package com.tim40.tim40.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tim40.tim40.model.enums.UserType;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "surname", nullable = false)
	private String surname;
	
	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "address", nullable = false)
	@Embedded
	private Address address;
	
	@Column(name = "usertype", nullable = false)
	@Enumerated(EnumType.STRING)
	private UserType userType;
	
	@Column(nullable = false)
	private boolean isFirstTimeLogging;

	public User() {
		super();
	}
	
	public User(Long id, String name, String surname, String email, String password, Address address, UserType userType,
			boolean isFirstTimeLogging) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.address = address;
		this.userType = userType;
		this.isFirstTimeLogging = isFirstTimeLogging;
	}
	
	public User(String name, String surname, String email, String password, Address address,
			UserType userType, boolean isFirstTimeLogging) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.address = address;
		this.userType = userType;
		this.isFirstTimeLogging = isFirstTimeLogging;
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
	
	public boolean isFirstTimeLogging() {
		return isFirstTimeLogging;
	}

	public void setFirstTimeLogging(boolean isFirstTimeLogging) {
		this.isFirstTimeLogging = isFirstTimeLogging;
	}
	
}

