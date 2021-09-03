package com.tim40.tim40.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "complaints")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Complaint {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "text", nullable = false)
	private String text;
	
	@Column(name = "reply", nullable = false)
	private String reply;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private SystemAdmin systemAdmin;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Patient patient;

}
