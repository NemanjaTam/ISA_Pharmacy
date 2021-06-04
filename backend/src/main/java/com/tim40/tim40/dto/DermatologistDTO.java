package com.tim40.tim40.dto;

import java.util.HashSet;
import java.util.Set;

import com.tim40.tim40.model.Address;
import com.tim40.tim40.model.Appointment;
import com.tim40.tim40.model.Dermatologist;
import com.tim40.tim40.model.DermatologistRating;
import com.tim40.tim40.model.Email;
import com.tim40.tim40.model.Patient;
import com.tim40.tim40.model.Pharmacy;
import com.tim40.tim40.model.enums.UserType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DermatologistDTO {
	private Long id;

	private String name;

	private String surname;

	private String email;

	private String password;

	private String phone;

	private Address address;

	private UserType userType;

	private boolean isFirstTimeLogging;

	private Set<Email> inbox = new HashSet<Email>();

	private Set<Email> send = new HashSet<Email>();

    private Set<Pharmacy> pharmacies = new HashSet<Pharmacy>();

	private Set<Appointment> appointments = new HashSet<Appointment>();

    private Set<DermatologistRating> ratings = new HashSet<DermatologistRating>();

	private Set<Patient> allPatients = new HashSet<Patient>();

	public DermatologistDTO(Dermatologist dermatologist) {
		this.id = dermatologist.getId();
		this.name = dermatologist.getName();
		this.surname = dermatologist.getSurname();
		this.email = dermatologist.getEmail();
		this.password = dermatologist.getPassword();
		this.phone = dermatologist.getPhone();
		this.address = dermatologist.getAddress();
		this.userType = dermatologist.getUserType();
		this.isFirstTimeLogging = dermatologist.isFirstTimeLogging();
		this.inbox = dermatologist.getInbox();
		this.send = dermatologist.getSend();
		this.pharmacies = dermatologist.getPharmacies();
		this.appointments = dermatologist.getAppointments();
		this.ratings = dermatologist.getRatings();
//		this.allPatients = dermatologist.getAllPatients();
	}
}
