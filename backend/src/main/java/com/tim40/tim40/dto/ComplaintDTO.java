package com.tim40.tim40.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class ComplaintDTO {
	
	private Long id;
	private String text;
	private String reply;
	private Long patId;
	private Long admId;
}
