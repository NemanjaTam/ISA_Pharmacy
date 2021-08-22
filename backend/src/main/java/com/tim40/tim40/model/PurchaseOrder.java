package com.tim40.tim40.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

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
	private final Set<QuantityMedicationPurchaseOrder> quantityMedicationsPurchase = new HashSet<QuantityMedicationPurchaseOrder>();
	
	@ManyToOne
	@JoinColumn(name = "pharmacy_administrator_id", nullable = false)
	private PharmacyAdministrator adminPharmacy;
	
	@Column(name = "purchase_status", nullable = false)
	@Enumerated(EnumType.STRING)
	private PurchaseOrderStatus purchaseOrderStatus;
	
//	@Embedded
//	private Period period;
	@JsonDeserialize(using = UnixToLocalDateTimeConverter.class)
	@Column(name = "starttime", nullable = true)
	private LocalDate startTime;
	
	@JsonDeserialize(using = UnixToLocalDateTimeConverter.class)
	@Column(name = "endtime", nullable = true)
	private LocalDate endTime;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_id", nullable = false)
	private Pharmacy pharmacy;
}
