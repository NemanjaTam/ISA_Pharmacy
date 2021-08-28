package com.tim40.tim40.model;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
<<<<<<< HEAD
=======

import org.apache.tomcat.jni.Address;

import com.fasterxml.jackson.annotation.JsonIgnore;

>>>>>>> 69abdcb991a0bde17260c870f785f5e30ea3f47e
import lombok.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DiscriminatorValue(value = "Pharmacy_admin")

public class PharmacyAdministrator extends User{
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_id", nullable = true)
	private Pharmacy pharmacy;

	/*@OneToMany(fetch = FetchType.LAZY,mappedBy="adminPharmacy")
	private  Set<PurchaseOrder> purchaseOrderMade = new HashSet<PurchaseOrder>();*/

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY,mappedBy="adminPharmacy")
	private  Set<PurchaseOrder> purchaseOrderMade = new HashSet<PurchaseOrder>();


}
