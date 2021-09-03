package com.tim40.tim40.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import com.tim40.tim40.model.enums.UserType;
import lombok.Getter;
import lombok.Setter;

	@Entity
	@DiscriminatorValue("System_admin")
	@Getter
	@Setter
	public class SystemAdmin extends User {

			@OneToMany(mappedBy = "systemAdmin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
			private Set<Complaint> complaints = new HashSet<Complaint>();
			
			public SystemAdmin() {}
			
		    public SystemAdmin(String name, String surname, String email, String password, Address address,  UserType userType, boolean isFirstTimeLogging, String phone)
		    {
		        super(name, surname, email, password, address, userType, isFirstTimeLogging, phone);
		    }
		    
		    
}