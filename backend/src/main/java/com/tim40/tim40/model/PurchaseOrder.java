package com.tim40.tim40.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

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
	
	@OneToMany(fetch = FetchType.LAZY)
	private final Set<QuantityMedication> quantityMedications = new HashSet<QuantityMedication>();
	
	@ManyToOne
	@JoinColumn(name = "pharmacy_administrator_id", nullable = false)
	private PharmacyAdministrator adminPharmacy;
	
	@Column(name = "purchase_status", nullable = false)
	@Enumerated(EnumType.STRING)
	private PurchaseOrderStatus purchaseOrderStatus;
	
	@Embedded
	private Period period;
}
