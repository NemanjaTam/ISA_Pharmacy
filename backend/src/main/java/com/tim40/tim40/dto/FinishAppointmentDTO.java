package com.tim40.tim40.dto;

import java.util.Set;

import com.tim40.tim40.model.Therapy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinishAppointmentDTO {
	
	private Long appointmentId;
	
	private String report;
	
	private Set<Therapy> therapies;
	
}
