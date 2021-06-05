package com.tim40.tim40.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tim40.tim40.model.Appointment;
import com.tim40.tim40.model.Therapy;
import com.tim40.tim40.repository.AppointmentRepository;

@Service
public class AppointmentService implements IAppointmentService {
	
	private AppointmentRepository appointmentRepository;

	@Autowired
	public AppointmentService(AppointmentRepository appointmentRepository) {
		this.appointmentRepository = appointmentRepository;
	}

	@Override
	public ResponseEntity<List<Appointment>> getAllScheduledForDermatologist(LocalDateTime date, Long id) {
		LocalDate local = date.toLocalDate();
		List<Appointment> appointments = appointmentRepository.findAll();
		List<Appointment> appointmentsForDate = new ArrayList<Appointment>();
		for(Appointment appointment : appointments) {
			LocalDate localDate = appointment.getPeriod().getStartTime().toLocalDate();
			if(localDate.compareTo(local) == 0) {
				appointmentsForDate.add(appointment);
			}
		}
 		List<Appointment> scheduledAppointments = new ArrayList<Appointment>();
		for(Appointment appointment : appointmentsForDate) {
			if(!appointment.isFinished() && appointment.isTaken() && appointment.getDermatologist().getId() == id) {
				scheduledAppointments.add(appointment);
			}
		}
		return new ResponseEntity<List<Appointment>>(scheduledAppointments,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Appointment> finishAppointment(Long id, String report, Set<Therapy> therapies) {
		Appointment appointment = appointmentRepository.findById(id).get();
		appointment.setReport(report);
		appointment.setTherapies(therapies);
		appointment.setFinished(true);
		appointmentRepository.save(appointment);
		return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);
	}
}
