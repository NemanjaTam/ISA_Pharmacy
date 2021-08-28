package com.tim40.tim40.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tim40.tim40.UnixToLocalDateTimeConverter;
import com.tim40.tim40.model.Period;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AbsenceDetailedDTO {
	private Long id;

	private boolean isApproved;
	
	private boolean isFinished;

	private Period period;
	
	private String name;
	
	private String surname;
}
