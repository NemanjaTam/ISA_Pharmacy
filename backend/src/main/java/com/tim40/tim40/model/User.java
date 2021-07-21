package com.tim40.tim40.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tim40.tim40.dto.UserDTO;
import com.tim40.tim40.model.enums.UserType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
	
	@Column(name = "phone", nullable = false)
	private String phone;
	
	@Column(name = "address", nullable = false)
	@Embedded
	private Address address;
	
	@Column(name = "usertype", nullable = false)
	@Enumerated(EnumType.STRING)
	private UserType userType;
	
	@Column(nullable = false)
	private boolean isFirstTimeLogging;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="userReceiver")
	private Set<Email> inbox = new HashSet<Email>();
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="userSender")
	private Set<Email> send = new HashSet<Email>();
	
	public User(String name, String surname, String email, String password, Address address,
			UserType userType, boolean isFirstTimeLogging, String phone) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.address = address;
		this.userType = userType;
		this.isFirstTimeLogging = isFirstTimeLogging;
		this.phone = phone;
	}

	public void update(UserDTO userDTO) {
		this.name = userDTO.getName();
		this.surname = userDTO.getSurname();
		this.email = userDTO.getEmail();
		this.password = userDTO.getPassword();
		this.phone = userDTO.getPhone();
		this.address = userDTO.getAddress();
		this.userType = userDTO.getUserType();
		this.isFirstTimeLogging = userDTO.isFirstTimeLogging();
		this.inbox = userDTO.getInbox();
		this.send = userDTO.getSend();
		this.phone = userDTO.getPhone();
	}
	
}

