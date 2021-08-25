package com.tim40.tim40.dto;

import java.util.ArrayList;
import java.util.List;

public class FilterMedicationDTO {

	private MedicationDTO medicationDTO;
	private List<MedicationPharmacyPriceDTO> medicationPharmaciesPrices = new ArrayList<>();
	
	public FilterMedicationDTO() { };
	
	public MedicationDTO getMedicationDTO() {
		return medicationDTO;
	}
	public void setMedicationDTO(MedicationDTO medicationDTO) {
		this.medicationDTO = medicationDTO;
	}
	public List<MedicationPharmacyPriceDTO> getMedicationPharmaciesPrices() {
		return medicationPharmaciesPrices;
	}
	public void setMedicationPharmaciesPrices(List<MedicationPharmacyPriceDTO> medicationPharmaciesPrices) {
		this.medicationPharmaciesPrices = medicationPharmaciesPrices;
	}
	
}
