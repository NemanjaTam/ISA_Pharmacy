package com.tim40.tim40.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PatientAllergedDTO {
	private Long patientId;
	private Long medicationId;
}
