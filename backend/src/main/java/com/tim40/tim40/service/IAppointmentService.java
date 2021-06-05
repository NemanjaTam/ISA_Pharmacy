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
}
