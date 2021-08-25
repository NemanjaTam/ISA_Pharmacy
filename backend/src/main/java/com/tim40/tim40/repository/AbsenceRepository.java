package com.tim40.tim40.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim40.tim40.model.Absence;

public interface AbsenceRepository extends JpaRepository<Absence, Long> {
	
	List<Absence> findByPharmacy(Long id);
}
