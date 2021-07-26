package com.tim40.tim40.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PeriodDTO {
	
	private LocalDateTime startTime;
	
	private LocalDateTime endTime;
}
