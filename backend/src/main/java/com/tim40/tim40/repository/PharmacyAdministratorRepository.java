package com.tim40.tim40.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim40.tim40.model.PharmacyAdministrator;

public interface PharmacyAdministratorRepository extends JpaRepository<PharmacyAdministrator, Long>{
	
    PharmacyAdministrator findById(long id);
    PharmacyAdministrator findByEmail(String email);
    List<PharmacyAdministrator> findAll();
}
