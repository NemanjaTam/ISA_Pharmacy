package com.tim40.tim40.dto;

import com.tim40.tim40.model.PurchaseOrderOffer;


public class PurchaseOrderOfferDTO {

	private Long id;
	private Long supplierId;
	private Long purchaseOrderId;
	private int offer;
	
	public PurchaseOrderOfferDTO() { }
	
	public PurchaseOrderOfferDTO(PurchaseOrderOffer purchaseOrderOffer) {
		this.id = purchaseOrderOffer.getId();
		this.supplierId = purchaseOrderOffer.getSupplier().getId();
		this.purchaseOrderId = purchaseOrderOffer.getPurchaseOrder().getId();
		this.offer = purchaseOrderOffer.getOffer();
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public Long getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(Long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public int getOffer() {
		return offer;
	}

	public void setOffer(int offer) {
		this.offer = offer;
	}
}
