package com.tim40.tim40.dto;

import java.util.HashSet;
import java.util.Set;

import com.tim40.tim40.model.Address;
import com.tim40.tim40.model.Email;
import com.tim40.tim40.model.PharmacyAdministrator;
import com.tim40.tim40.model.PurchaseOrder;
import com.tim40.tim40.model.enums.TypeOfMedication;
import com.tim40.tim40.model.enums.UserType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
public class PharmacyAdminDTO {
	
	private Long id;

	private String name;

	private String surname;

	private String email;

	private String password;

	private String phone;

	private String state;
	private String city;
	private String street;
	private String number;
	private int postalCode;

	private UserType userType;

	private boolean isFirstTimeLogging;

	private Set<Email> inbox = new HashSet<Email>();

	private Set<Email> send = new HashSet<Email>();
    
	private  Set<PurchaseOrder> purchaseOrderMade = new HashSet<PurchaseOrder>();

	public PharmacyAdminDTO(PharmacyAdministrator a) {
		super();
		this.name = a.getName();
		this.surname = a.getSurname();
		this.email = a.getEmail();
		this.password = a.getPassword();
		this.phone = a.getPhone();
		this.state = a.getAddress().getState();
		this.city = a.getAddress().getCity();
		this.street =a.getAddress().getStreet();
		this.number = a.getAddress().getNumber();
		this.postalCode = a.getAddress().getPostalCode();
		this.userType = userType;
		this.isFirstTimeLogging = isFirstTimeLogging;
		this.inbox = inbox;
		this.send = send;
		this.purchaseOrderMade = purchaseOrderMade;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public Set<Email> getInbox() {
		return inbox;
	}

	public void setInbox(Set<Email> inbox) {
		this.inbox = inbox;
	}

	public Set<Email> getSend() {
		return send;
	}

	public void setSend(Set<Email> send) {
		this.send = send;
	}

	public Set<PurchaseOrder> getPurchaseOrderMade() {
		return purchaseOrderMade;
	}

	public void setPurchaseOrderMade(Set<PurchaseOrder> purchaseOrderMade) {
		this.purchaseOrderMade = purchaseOrderMade;
	}

  

   
	
	

}
