package com.tim40.tim40.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tim40.tim40.UnixToLocalDateTimeConverter;
import com.tim40.tim40.dto.AbsenceDTO;
import com.tim40.tim40.model.enums.AbsenceType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "absences")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Absence {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private boolean isApproved;
	
	@Column
	private boolean isFinished;
	 
	@JsonDeserialize(using = UnixToLocalDateTimeConverter.class)
	@Column(name = "starttime", nullable = true)
	private LocalDate startTime;
	
	@JsonDeserialize(using = UnixToLocalDateTimeConverter.class)
	@Column(name = "endtime", nullable = true)
	private LocalDate endTime;
	
	@Column()
	@Enumerated(EnumType.STRING)
	private AbsenceType type;
	
	@ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
	private User user;

	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_id", nullable = true)
	@JsonIgnore
	private Pharmacy pharmacy;
	
	public Absence(AbsenceDTO absenceDTO) {
		this.isApproved = absenceDTO.isApproved();
		this.isFinished = absenceDTO.isFinished();
//		this.endTime = absenceDTO.get
		this.user = absenceDTO.getUser();
	}
	
	
}
