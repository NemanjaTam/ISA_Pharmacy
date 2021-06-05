package com.tim40.tim40.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim40.tim40.dto.AbsenceCheckDTO;
import com.tim40.tim40.dto.FinishAppointmentDTO;
import com.tim40.tim40.model.Appointment;
import com.tim40.tim40.service.AppointmentService;

@RestController
@RequestMapping(value = "api/appointment")
public class AppointmentController {
	
	private AppointmentService appointmentService;

	@Autowired
	public AppointmentController(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}
	
	@PostMapping(value = "/get-all-scheduled-appointments", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> getAllScheduledForDermatologist(@RequestBody AbsenceCheckDTO absenceCheckDTO) throws Exception{
		return appointmentService.getAllScheduledForDermatologist(absenceCheckDTO.getSelectedDate(), absenceCheckDTO.getUserId());
	}
	
	@PostMapping(value = "/finish-appointment", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Appointment> finishAppointment(@RequestBody FinishAppointmentDTO finishAppointmentDTO) throws Exception{
		return appointmentService.finishAppointment(finishAppointmentDTO.getAppointmentId(), finishAppointmentDTO.getReport(), finishAppointmentDTO.getTherapies());
	}
}
