package com.tim40.tim40.service;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;

import com.tim40.tim40.dto.AbsenceDTO;


public interface IAbsenceService {
	ResponseEntity<AbsenceDTO> requestAbsence(AbsenceDTO absenceDTO);
	ResponseEntity<String> checkIsUserOnAbsence(LocalDateTime date, Long userId);
}
