package com.tim40.tim40.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.tim40.tim40.dto.ConsultationDTO;
import com.tim40.tim40.model.Consultation;
import com.tim40.tim40.model.Therapy;

public interface IConsultationService {
	ResponseEntity<List<Consultation>> getAllScheduledForPharmacist(LocalDateTime date, Long id);
	ResponseEntity<Consultation> finishConsultation(Long id, String report, Set<Therapy> therapies);
	ResponseEntity<List<Consultation>> getAvailableConsultations(LocalDateTime start, LocalDateTime end, Long pharmacyId, Long patientId, Long pharmacistId);
	ResponseEntity<Consultation> scheduleConsultation(ConsultationDTO consultationDTO);
}
