package com.tim40.tim40.repository;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import com.tim40.tim40.model.Medication;

public interface MedicationRepository extends JpaRepository<Medication, Long>{

}
 