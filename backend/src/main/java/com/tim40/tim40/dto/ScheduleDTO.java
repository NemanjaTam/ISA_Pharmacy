package com.tim40.tim40.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {
	private Long appointmentId;
	private Long patientId;
	private Long dermatologistId;
}
