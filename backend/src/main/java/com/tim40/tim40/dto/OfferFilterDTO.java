package com.tim40.tim40.dto;

public class OfferFilterDTO {

	private String status;
	
	public OfferFilterDTO () {}
	
	public OfferFilterDTO(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
