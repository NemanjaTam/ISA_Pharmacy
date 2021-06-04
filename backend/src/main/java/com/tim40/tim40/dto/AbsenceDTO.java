package com.tim40.tim40.dto;

import com.tim40.tim40.model.Absence;
import com.tim40.tim40.model.Period;
import com.tim40.tim40.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AbsenceDTO {

	private Long id;

	private boolean isApproved;
	
	private boolean isFinished;

	private Period period;

	private User user;

	public AbsenceDTO(Absence absence) {
		this.id = absence.getId();
		this.isApproved = absence.isApproved();
		this.isFinished = absence.isFinished();
		this.period = absence.getPeriod();
		this.user = absence.getUser();
	}
	
	
}
