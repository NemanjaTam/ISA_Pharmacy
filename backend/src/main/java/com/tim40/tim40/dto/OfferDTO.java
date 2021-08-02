package com.tim40.tim40.dto;

import com.tim40.tim40.model.PurchaseOrderOffer;

public class OfferDTO {

	private Long id;
	private Long supplierID;
	private Long purchaseOrderID;
	private int offer;
	private String status;
	
	public OfferDTO() {}
	
	public OfferDTO(PurchaseOrderOffer purchaseOrderOffer) {
		this.id = purchaseOrderOffer.getId();
		this.offer = purchaseOrderOffer.getOffer();
		this.purchaseOrderID = purchaseOrderOffer.getPurchaseOrder().getId();
		this.supplierID = purchaseOrderOffer.getSupplier().getId();
		this.setStatus(purchaseOrderOffer.getStatus());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(Long supplierID) {
		this.supplierID = supplierID;
	}

	public Long getPurchaseOrderID() {
		return purchaseOrderID;
	}

	public void setPurchaseOrderID(Long purchaseOrderID) {
		this.purchaseOrderID = purchaseOrderID;
	}

	public int getOffer() {
		return offer;
	}

	public void setOffer(int offer) {
		this.offer = offer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
