package com.tim40.tim40.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Period {
	@Column(name = "starttime", nullable = true)
	private Date startTime;
	
	@Column(name = "endtime", nullable = true)
	private Date endTime;
}
