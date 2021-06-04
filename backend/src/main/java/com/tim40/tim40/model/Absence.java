package com.tim40.tim40.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tim40.tim40.dto.AbsenceDTO;

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
	
	@Embedded  				//jedno oduststvo ima jedan period vazenja,a za jedan period vazenja se vezuje jedno odsustvo
	private Period period;
	
	@ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Absence(AbsenceDTO absenceDTO) {
		this.isApproved = absenceDTO.isApproved();
		this.isFinished = absenceDTO.isFinished();
		this.period = absenceDTO.getPeriod();
		this.user = absenceDTO.getUser();
	}
	
	
}
