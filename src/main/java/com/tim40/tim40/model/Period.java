package com.tim40.tim40.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Period {
	@Column(name = "starttime", nullable = false)
	private LocalDateTime startTime;
	
	@Column(name = "endtime", nullable = false)
	private LocalDateTime endTime;
	
	public Period() {
		super();
	}
	
	public Period(LocalDateTime startTime, LocalDateTime endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public LocalDateTime getStart() {
		return startTime;
	}

	public void setStart(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEnd() {
		return endTime;
	}

	public void setEnd(LocalDateTime endTime) {
		this.endTime = endTime;
	}
}
