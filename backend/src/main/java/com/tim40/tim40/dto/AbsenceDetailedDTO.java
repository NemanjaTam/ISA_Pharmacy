package com.tim40.tim40.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tim40.tim40.UnixToLocalDateTimeConverter;
import com.tim40.tim40.model.Period;
import com.tim40.tim40.model.enums.AbsenceType;

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
	
	private AbsenceType type;
	
	private boolean isFinished;


	private LocalDate startTime;

	private LocalDate endTime;
	
	private String name;
	
	private String surname;
}
