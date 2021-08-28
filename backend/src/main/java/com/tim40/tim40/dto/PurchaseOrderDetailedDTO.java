package com.tim40.tim40.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PurchaseOrderDetailedDTO {
	 private Long adminId;
	 @JsonProperty("endTime")
	 private LocalDate endTime;
	 private List<MedicationQuantityDTO> medicationDTO;
	 @JsonProperty("startTime")
	 private LocalDate startTime;
	 private Long pharmacyId;
	 
}
