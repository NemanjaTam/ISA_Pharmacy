/*package com.tim40.tim40.model;

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
@DiscriminatorValue("Supplier")
@Getter
@Setter
@EqualsAndHashCode
public class Supplier extends User {

		@ManyToMany(mappedBy = "suppliers")
	    private final Set<Pharmacy> pharmacies = new HashSet<Pharmacy>();

		public Supplier() {}
		
	    public Supplier(String name, String surname, String email, String password, Address address, UserType userType, boolean isFirstTimeLogging)
	    {
	        super(name, surname, email, password, address, userType, isFirstTimeLogging);
	    }
	    
	    public Supplier(Long id, String name, String surname, String email, String password, Address address, UserType userType, boolean isFirstTimeLogging)
	    {
	    	super(id, name, surname, email, password, address, userType, isFirstTimeLogging);
	    }
*/