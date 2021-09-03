package com.tim40.tim40.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tim40.tim40.UnixToLocalDateTimeConverter;
import com.tim40.tim40.model.enums.PurchaseOrderStatus;

import lombok.*;

@Entity
@Table(name = "purchase_order_list")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PurchaseOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<QuantityMedication> quantityMedications= new HashSet<QuantityMedication>();
	
	@ManyToOne
	@JoinColumn(name = "pharmacy_administrator_id", nullable = false)
	private PharmacyAdministrator adminPharmacy;
	
	@Column(name = "purchase_status", nullable = false)
	@Enumerated(EnumType.STRING)
	private PurchaseOrderStatus purchaseOrderStatus;
	
	// @OneToMany(fetch = FetchType.LAZY, mappedBy = "purchaseOrder")
	// private final Set<PurchaseOrderOffer> purchaseOrderOffers = new HashSet<PurchaseOrderOffer>();

	@Embedded
	private Period period;
	
	public void setQuantityMedications(Set<QuantityMedication> quantityMedications) {
		this.quantityMedications = quantityMedications;
	}

	//@JsonDeserialize(using = UnixToLocalDateTimeConverter.class)
/*	@Column(name = "starttime", nullable = true)
	private LocalDate startTime;
	
	@JsonDeserialize(using = UnixToLocalDateTimeConverter.class)
	@Column(name = "endtime", nullable = true)
	private LocalDate endTime;*/
	
/*	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_id", nullable = false)
	private Pharmacy pharmacy;*/

	/*@OneToMany(fetch = FetchType.LAZY,mappedBy="purchaseOrder",cascade = CascadeType.ALL)
	private List<Offer> offers;*/
}
