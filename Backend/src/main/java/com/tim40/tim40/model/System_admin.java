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
	@DiscriminatorValue("System_admin")
	@Getter
	@Setter
	@EqualsAndHashCode
	public class System_admin extends User {

			@ManyToMany(mappedBy = "system_admins")
		    private final Set<Pharmacy> pharmacies = new HashSet<Pharmacy>();

			public System_admin() {}
			
		    public System_admin(String name, String surname, String email, String password, Address address,  UserType userType, boolean isFirstTimeLogging, String phone)
		    {
		        super(name, surname, email, password, address, userType, isFirstTimeLogging, phone);
		    }
		    
		    
}
