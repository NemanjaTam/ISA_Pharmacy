package com.tim40.tim40.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim40.tim40.dto.AbsenceCheckDTO;
import com.tim40.tim40.dto.AbsenceDetailedDTO;
import com.tim40.tim40.dto.AppointmentSchedulingDTO;
import com.tim40.tim40.dto.FinishAppointmentDTO;
import com.tim40.tim40.dto.NewAppointmentDTO;
import com.tim40.tim40.dto.ScheduleDTO;
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
	
	@PostMapping(value = "/get-available-appointments", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> getAvailableAppointments(@RequestBody AppointmentSchedulingDTO appointmentSchedulingDTO) throws Exception{
		return appointmentService.getAvailableAppointments(
				appointmentSchedulingDTO.getFromDate(), 
				appointmentSchedulingDTO.getToDate(), 
				appointmentSchedulingDTO.getPharmacyId(), 
				appointmentSchedulingDTO.getPatientId(), 
				appointmentSchedulingDTO.getDermatologistId());
	}
	
	@PostMapping(value = "/schedule-appointment", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Appointment> scheduleAppointment(@RequestBody ScheduleDTO scheduleDTO) throws Exception{
		return appointmentService.scheduleAppointment(scheduleDTO.getAppointmentId(), scheduleDTO.getPatientId(), scheduleDTO.getDermatologistId());
	}
	
	//za godisnji prikaz
	@GetMapping(value="/get-report-for-year/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String,Integer>> getYearlyReportForAppointments(@PathVariable("id") Long id,@RequestHeader("usertype") String type){
		if("PHARMACY_ADMINISTRATOR".equals(type)){
			return new ResponseEntity<Map<String,Integer>>(this.appointmentService.getDoneAppointmentsForYear(id), HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Map<String,Integer>>(HttpStatus.UNAUTHORIZED);
			}
		
	}
	
	
	@GetMapping(value="/get-report-for-quartal/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String,Integer>> getQuartalReportForAppointments(@PathVariable("id") Long id,@RequestHeader("usertype") String type){
		if("PHARMACY_ADMINISTRATOR".equals(type)){
			return new ResponseEntity<Map<String,Integer>>(this.appointmentService.getDoneAppointmentsForQuartal(id), HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Map<String,Integer>>(HttpStatus.UNAUTHORIZED);
			}
		
	}
	
	@GetMapping(value="/get-report-for-month/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String,Integer>> getMonthlyReportForAppointments(@PathVariable("id") Long id,@RequestHeader("usertype") String type){
		if("PHARMACY_ADMINISTRATOR".equals(type)){
			return new ResponseEntity<Map<String,Integer>>(this.appointmentService.getDoneAppointmentsForMonth(id), HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Map<String,Integer>>(HttpStatus.UNAUTHORIZED);
			}
		
	}
	
	
	@PostMapping(value="/create-appointment/",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> createAppointment(@RequestHeader("usertype") String type,@RequestBody NewAppointmentDTO dto){
		if("PHARMACY_ADMINISTRATOR".equals(type)){
			return new ResponseEntity<Integer>( this.appointmentService.createAppointment(dto) ,HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Integer>(HttpStatus.UNAUTHORIZED);
			}
		
	}
	
}
