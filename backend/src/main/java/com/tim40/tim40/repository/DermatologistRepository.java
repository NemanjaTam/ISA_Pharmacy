package com.tim40.tim40.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import com.tim40.tim40.model.Dermatologist;

public interface DermatologistRepository extends JpaRepository<Dermatologist, Long>{
	Optional<Dermatologist> findByEmail(String email);
	
//	obican sql ali mu se prosledjuje parametar
	@Query(value = "SELECT * FROM USERS,DERMATOLOGIST_PHARMACY WHERE USERS.id = DERMATOLOGIST_PHARMACY.dermatologist_id and DERMATOLOGIST_PHARMACY.pharmacy_id = :ID", 
			  nativeQuery = true)
	public List<Dermatologist> getAllByPharmacyId(@Param("ID") Long id);
	
	
}
