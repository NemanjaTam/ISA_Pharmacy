package com.tim40.tim40.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tim40.tim40.dto.AbsenceDTO;
import com.tim40.tim40.model.Absence;

public interface IAbsenceService {
	ResponseEntity<AbsenceDTO> requestAbsence(AbsenceDTO absenceDTO);
	ResponseEntity<String> checkIsUserOnAbsence(LocalDateTime date, Long userId);
	List<Absence> getAllAbsenceForPharmacyId(Long id);
}
