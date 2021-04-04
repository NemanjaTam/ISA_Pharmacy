package com.tim40.tim40.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim40.tim40.model.Medication;

public interface MedicationRepository extends JpaRepository<Medication, Long>{

}
