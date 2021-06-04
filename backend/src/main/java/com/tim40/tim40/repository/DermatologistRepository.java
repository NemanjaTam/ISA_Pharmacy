package com.tim40.tim40.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim40.tim40.model.Dermatologist;

public interface DermatologistRepository extends JpaRepository<Dermatologist, Long>{
	Optional<Dermatologist> findByEmail(String email);
}
