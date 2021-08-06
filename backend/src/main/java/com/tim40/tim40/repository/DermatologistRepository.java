package com.tim40.tim40.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import com.tim40.tim40.dto.DermatologistDTO;
import com.tim40.tim40.model.Dermatologist;
import com.tim40.tim40.projections.DermatologistDetailsProjection;
import com.tim40.tim40.projections.DermatologistProjection;

public interface DermatologistRepository extends JpaRepository<Dermatologist, Long>{
	Optional<Dermatologist> findByEmail(String email);
	
//	obican sql ali mu se prosledjuje parametar,ne menjati nista
	@Query(value = "SELECT email,u.id, u.name,surname, PHARMACIES.name AS pharmacyName FROM USERS AS u FULL OUTER JOIN "
			+ "DERMATOLOGIST_PHARMACY ON u.id = DERMATOLOGIST_PHARMACY.dermatologist_id FULL OUTER JOIN PHARMACIES"
			+ " ON PHARMACIES.id = DERMATOLOGIST_PHARMACY.pharmacy_id WHERE DERMATOLOGIST_PHARMACY.pharmacy_id = :ID", 
			  nativeQuery = true)
	public List<DermatologistProjection> getAllByPharmacyId(@Param("ID") Long id);
	
	//ne menjati koristi se
	@Query(value = "SELECT email,u.id, u.name,surname, PHARMACIES.name AS pharmacyName"
			+ " FROM USERS AS u FULL OUTER JOIN DERMATOLOGIST_PHARMACY"
			+ " ON  u.id = DERMATOLOGIST_PHARMACY.dermatologist_id FULL OUTER JOIN PHARMACIES"
			+ " ON PHARMACIES.id = DERMATOLOGIST_PHARMACY.pharmacy_id"
			+ " WHERE u.usertype = 'DERMATOLOGIST' AND DERMATOLOGIST_PHARMACY", 
			  nativeQuery = true)
	public List<DermatologistDetailsProjection> getAllByPharmacyIdWithPharmacyList();
	
	
}
