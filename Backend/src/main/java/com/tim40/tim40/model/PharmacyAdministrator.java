package com.tim40.tim40.model;
import javax.persistence.*;
import com.tim40.tim40.model.enums.UserType;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode
@DiscriminatorValue(value = "Pharmacy_administrator")
public class PharmacyAdministrator extends User{


	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_id", nullable = true)
	private Pharmacy pharmacy;
	
	public PharmacyAdministrator() {}
	
    public PharmacyAdministrator(String name, String surname, String email, String password, Address address, Pharmacy pharmacy, boolean isFirstTimeLogging)
    {
        super(name, surname, email, password, address, UserType.PHARMACY_ADMINISTRATOR, isFirstTimeLogging);
        this.pharmacy = pharmacy;
    }
    
	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}
    
}
