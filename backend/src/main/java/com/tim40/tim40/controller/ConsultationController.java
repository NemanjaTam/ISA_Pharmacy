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
import com.tim40.tim40.model.Consultation;
import com.tim40.tim40.service.ConsultationService;

@RestController
@RequestMapping(value = "api/consultation")
public class ConsultationController {

	private ConsultationService consultationService;

	@Autowired
	public ConsultationController(ConsultationService consultationService) {
		this.consultationService = consultationService;
	}
	
	@PostMapping(value = "/get-all-scheduled-consultations", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Consultation>> getAllScheduledForPharmacist(@RequestBody AbsenceCheckDTO absenceCheckDTO) throws Exception{
		return consultationService.getAllScheduledForPharmacist(absenceCheckDTO.getSelectedDate(), absenceCheckDTO.getUserId());
	}
	
	@PostMapping(value = "/finish-consultation", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Consultation> finishConsultation(@RequestBody FinishAppointmentDTO finishAppointmentDTO) throws Exception{
		return consultationService.finishConsultation(finishAppointmentDTO.getAppointmentId(), finishAppointmentDTO.getReport(), finishAppointmentDTO.getTherapies());
	}
	
}
