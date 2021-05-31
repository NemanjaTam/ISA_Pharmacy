package com.tim40.tim40.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.tim40.tim40.model.enums.UserType;

import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("Dermatologist")
@Getter
@Setter
public class Dermatologist extends User {
	@ManyToMany(mappedBy = "dermatologists")
    private final Set<Pharmacy> pharmacies = new HashSet<Pharmacy>();
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="dermatologists")
    private Set<DermatologistRating> ratings;

	public Dermatologist() {}
	
    public Dermatologist(String name, String surname, String email, String password, Address address, UserType userType, boolean isFirstTimeLogging)
    {
        super(name, surname, email, password, address, userType, isFirstTimeLogging);
    }
    
   
}