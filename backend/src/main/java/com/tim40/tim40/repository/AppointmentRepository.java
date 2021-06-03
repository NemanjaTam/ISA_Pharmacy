package com.tim40.tim40.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim40.tim40.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
