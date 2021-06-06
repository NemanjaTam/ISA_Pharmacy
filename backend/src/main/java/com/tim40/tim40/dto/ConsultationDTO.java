package com.tim40.tim40.dto;

import java.util.HashSet;
import java.util.Set;

import com.tim40.tim40.model.Patient;
import com.tim40.tim40.model.Period;
import com.tim40.tim40.model.Pharmacist;
import com.tim40.tim40.model.Pharmacy;
import com.tim40.tim40.model.Therapy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationDTO {

	private Long id;

	private String report;

	private PeriodDTO period;

	private boolean isTaken;

	private boolean isFinished; 

    private Pharmacy pharmacy;

    private  Pharmacist pharmacist;

    private Patient patient;

    private Set<Therapy> therapies = new HashSet<Therapy>();
}
