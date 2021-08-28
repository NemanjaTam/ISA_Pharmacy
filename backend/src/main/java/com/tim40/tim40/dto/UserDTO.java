package com.tim40.tim40.dto;

import java.util.HashSet;
import java.util.Set;

import com.tim40.tim40.model.Address;
import com.tim40.tim40.model.Email;
import com.tim40.tim40.model.User;
import com.tim40.tim40.model.enums.UserType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private Long id;

	private String name;

	private String surname;
	
	private String email;

	private String password;

	private Address address;
	
	private String phone;
	
	private UserType userType;
	
	private boolean isFirstTimeLogging;

	private Set<Email> inbox = new HashSet<Email>();
	
	private Set<Email> send = new HashSet<Email>();
	

	public UserDTO(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.address = user.getAddress();
		this.phone = user.getPhone();
		this.userType = user.getUserType();
		this.isFirstTimeLogging = user.isFirstTimeLogging();
		this.inbox = user.getInbox();
		this.send = user.getSend();
	}
	
}
