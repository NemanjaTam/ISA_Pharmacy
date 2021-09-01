package com.tim40.tim40.dto;
import java.util.Date;
import com.tim40.tim40.model.PurchaseOrderOffer;

public class PurchaseOrderOfferDTO {


	private Long id;
	private Long supplierId;
	private Long purchaseOrderId;
	private int offer;
	private String status;
	private Date deliveryDeadline;
	
	public PurchaseOrderOfferDTO() { }
	
	public PurchaseOrderOfferDTO(PurchaseOrderOffer purchaseOrderOffer) {
		this.id = purchaseOrderOffer.getId();
		this.supplierId = purchaseOrderOffer.getSupplier().getId();
		this.purchaseOrderId = purchaseOrderOffer.getPurchaseOrder().getId();
		this.offer = purchaseOrderOffer.getOffer();
		this.status = purchaseOrderOffer.getStatus();
		this.deliveryDeadline = purchaseOrderOffer.getDeliveryDeadline();
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDeliveryDeadline() {
		return deliveryDeadline;
	}

	public void setDeliveryDeadline(Date deliveryDeadline) {
		this.deliveryDeadline = deliveryDeadline;
	}
}
