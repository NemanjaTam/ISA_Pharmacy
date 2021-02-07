package com.tim40.tim40.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Period {
	@Column(name = "starttime", nullable = false)
	private LocalDate startTime;
	
	@Column(name = "endtime", nullable = false)
	private LocalDate endTime;
	
	public Period() {
		super();
	}
	
	public Period(LocalDate startTime, LocalDate endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public LocalDate getStart() {
		return startTime;
	}

	public void setStart(LocalDate startTime) {
		this.startTime = startTime;
	}

	public LocalDate getEnd() {
		return endTime;
	}

	public void setEnd(LocalDate endTime) {
		this.endTime = endTime;
	}
}
