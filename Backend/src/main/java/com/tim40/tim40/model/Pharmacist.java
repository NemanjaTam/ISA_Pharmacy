package com.tim40.tim40.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tim40.tim40.model.enums.UserType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("Pharmacist")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Pharmacist extends User {
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_id", nullable = true)
    private Pharmacy pharmacy;

	public Pharmacist() {}
	
    public Pharmacist(String name, String surname, String email, String password, Address address, UserType userType, Pharmacy pharmacy, boolean isFirstTimeLogging, String phone)
    {
        super(name, surname, email, password, address, userType, isFirstTimeLogging, phone);
        this.pharmacy = pharmacy;
    }


}
