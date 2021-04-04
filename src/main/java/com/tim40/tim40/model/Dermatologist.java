package com.tim40.tim40.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import com.tim40.tim40.model.enums.UserType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("Dermatologist")
@Getter
@Setter
@EqualsAndHashCode
public class Dermatologist extends User {
	@ManyToMany(mappedBy = "dermatologists")
    private final Set<Pharmacy> pharmacies = new HashSet<Pharmacy>();

    public Dermatologist(String name, String surname, String email, String password, Address address, UserType userType, boolean isFirstTimeLogging)
    {
        super(name, surname, email, password, address, userType, isFirstTimeLogging);
    }
    
    public Dermatologist(Long id, String name, String surname, String email, String password, Address address, UserType userType, boolean isFirstTimeLogging)
    {
    	super(id, name, surname, email, password, address, userType, isFirstTimeLogging);
    }
}