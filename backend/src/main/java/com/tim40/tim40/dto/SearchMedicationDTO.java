package com.tim40.tim40.dto;

import com.tim40.tim40.model.enums.TypeOfMedication;

public class SearchMedicationDTO {

	private String name;
	private int avgRate;
	private TypeOfMedication typeOfMedication;
	
	public SearchMedicationDTO() {}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAvgRate() {
		return avgRate;
	}
	public void setAvgRate(int avgRate) {
		this.avgRate = avgRate;
	}
	public TypeOfMedication getTypeOfMedication() {
		return typeOfMedication;
	}
	public void setTypeOfMedication(TypeOfMedication typeOfMedication) {
		this.typeOfMedication = typeOfMedication;
	}
}
