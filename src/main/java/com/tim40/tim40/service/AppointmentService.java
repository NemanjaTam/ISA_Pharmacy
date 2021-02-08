package com.tim40.tim40.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim40.tim40.repository.AppointmentRepository;

@Service
public class AppointmentService {
	
	private AppointmentRepository appointmentRepository;

	@Autowired
	public AppointmentService(AppointmentRepository appointmentRepository) {
		this.appointmentRepository = appointmentRepository;
	}
	
}
