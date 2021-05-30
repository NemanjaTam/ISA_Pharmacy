package com.tim40.tim40.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim40.tim40.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

}
