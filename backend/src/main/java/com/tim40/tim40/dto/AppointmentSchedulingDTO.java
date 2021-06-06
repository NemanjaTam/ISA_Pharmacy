package com.tim40.tim40.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tim40.tim40.UnixToLocalDateTimeConverter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentSchedulingDTO {
	@JsonDeserialize(using = UnixToLocalDateTimeConverter.class)
	private LocalDateTime fromDate;
	@JsonDeserialize(using = UnixToLocalDateTimeConverter.class)
	private LocalDateTime toDate;
	private Long pharmacyId;
	private Long patientId;
	private Long dermatologistId;
}
