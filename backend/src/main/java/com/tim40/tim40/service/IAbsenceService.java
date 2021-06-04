package com.tim40.tim40.service;

import org.springframework.http.ResponseEntity;

import com.tim40.tim40.dto.AbsenceDTO;


public interface IAbsenceService {
	ResponseEntity<AbsenceDTO> requestAbsence(AbsenceDTO absenceDTO);
}
