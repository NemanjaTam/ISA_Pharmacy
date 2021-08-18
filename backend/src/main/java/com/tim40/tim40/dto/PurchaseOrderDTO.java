package com.tim40.tim40.dto;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class PurchaseOrderDTO {
	 private Long adminId;
	 @JsonProperty("endTime")
	 private LocalDate endTime;
	 private List<MedicationQuantityDTO> medicationDTO;
	 @JsonProperty("startTime")
	 private LocalDate startTime;
	 
	public PurchaseOrderDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PurchaseOrderDTO(Long adminId, LocalDate endTime, List<MedicationQuantityDTO> medicationDTO,
			LocalDate startTime) {
		super();
		this.adminId = adminId;
		this.endTime = endTime;
		this.medicationDTO = medicationDTO;
		this.startTime = startTime;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public LocalDate getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDate endTime) {
		this.endTime = endTime;
	}

	public List<MedicationQuantityDTO> getMedicationDTO() {
		return medicationDTO;
	}

	public void setMedicationDTO(List<MedicationQuantityDTO> medicationDTO) {
		this.medicationDTO = medicationDTO;
	}

	public LocalDate getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDate startTime) {
		this.startTime = startTime;
	}
	

	 
}
