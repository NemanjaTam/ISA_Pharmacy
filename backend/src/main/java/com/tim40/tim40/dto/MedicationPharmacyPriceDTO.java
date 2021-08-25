package com.tim40.tim40.dto;

import com.tim40.tim40.model.Pharmacy;

public class MedicationPharmacyPriceDTO {

	private PharmacyDTO pharmacy;
	private float price;
	
	public MedicationPharmacyPriceDTO() { }

	public MedicationPharmacyPriceDTO(Pharmacy pharmacy, float price) {
		this.pharmacy = new PharmacyDTO(pharmacy);
		this.price = price;
	}
	
	public PharmacyDTO getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(PharmacyDTO pharmacy) {
		this.pharmacy = pharmacy;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	};
}
