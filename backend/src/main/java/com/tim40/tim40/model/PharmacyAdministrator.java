package com.tim40.tim40.model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DiscriminatorValue(value = "PharmacyAdministrator")
public class PharmacyAdministrator extends User{
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_id", nullable = true)
	private Pharmacy pharmacy;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="adminPharmacy")
	private final Set<PurchaseOrder> purchaseOrderMade = new HashSet<PurchaseOrder>();
}
