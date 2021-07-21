package com.tim40.tim40.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.tim40.tim40.model.enums.UserType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("Supplier")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Supplier extends User {

	    @OneToMany(fetch = FetchType.LAZY, mappedBy = "supplier")
	    private final Set<PurchaseOrderOffer> purchaseOrderOffers = new HashSet<PurchaseOrderOffer>();
	 
		@ManyToMany(mappedBy = "suppliers")
	    private final Set<Pharmacy> pharmacies = new HashSet<Pharmacy>();

		public Supplier() {}
		
	    public Supplier(String name, String surname, String email, String password, Address address,  UserType userType, boolean isFirstTimeLogging, String phone)
	    {
	        super(name, surname, email, password, address, userType, isFirstTimeLogging, phone);
	    }
	    
	   
}