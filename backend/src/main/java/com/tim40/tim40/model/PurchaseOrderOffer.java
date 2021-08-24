package com.tim40.tim40.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "purchase_order_offer_list")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PurchaseOrderOffer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "supplier_id", nullable = false)
	private Supplier supplier;

	@ManyToOne
	@JoinColumn(name = "purchase_order_id", nullable = false)
	private PurchaseOrder purchaseOrder;
	
	private String status;
	private int offer;
	private Date deliveryDeadline;
	
}
