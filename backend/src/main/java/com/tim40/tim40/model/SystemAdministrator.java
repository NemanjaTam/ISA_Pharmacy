package com.tim40.tim40.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DiscriminatorValue(value = "SystemAdministrator")

public class SystemAdministrator extends User{
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_id", nullable = true)
	private Pharmacy pharmacy;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="systemAdminPharmacy")
	private  Set<PurchaseOrder> purchaseOrderMade = new HashSet<PurchaseOrder>();
	
   
}
