package com.tim40.tim40.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tim40.tim40.UnixToLocalDateTimeConverter;

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
	
	@JsonDeserialize(using = UnixToLocalDateTimeConverter.class)
	@Column(name = "starttime", nullable = true)
	private LocalDateTime startTime;
	
	@JsonDeserialize(using = UnixToLocalDateTimeConverter.class)
	@Column(name = "endtime", nullable = true)
	private LocalDateTime endTime;
}
