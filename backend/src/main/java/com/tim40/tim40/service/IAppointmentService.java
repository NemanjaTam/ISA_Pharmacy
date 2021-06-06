package com.tim40.tim40.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.tim40.tim40.model.Appointment;
import com.tim40.tim40.model.Therapy;

public interface IAppointmentService {
	ResponseEntity<List<Appointment>> getAllScheduledForDermatologist(LocalDateTime date, Long id);
	ResponseEntity<Appointment> finishAppointment(Long id, String report, Set<Therapy> therapies);
	ResponseEntity<List<Appointment>> getAvailableAppointments(LocalDateTime start, LocalDateTime end, Long pharmacyId, Long patientId, Long dermatologistId);
	ResponseEntity<Appointment> scheduleAppointment(Long appointmentId, Long patientId, Long dermatologistId);
}
