package com.tim40.tim40.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import com.tim40.tim40.dto.DermatologistDTO;
import com.tim40.tim40.model.Dermatologist;
import com.tim40.tim40.projections.DermatologistDetailsProjection;
import com.tim40.tim40.projections.DermatologistPharmacyProjection;
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
//	@Query(value = "SELECT PHARMACIES.name as pharmacyName FROM PHARMACIES WHERE "
//			+"PHARMACIES.id in ("
//			+ "SELECT email,u.id, u.name,surname"
//			+ " FROM USERS AS u FULL OUTER JOIN DERMATOLOGIST_PHARMACY"
//			+ " ON  u.id = DERMATOLOGIST_PHARMACY.dermatologist_id"
//			+ " WHERE u.usertype = 'DERMATOLOGIST')",
//			  nativeQuery = true)
	@Query(value="select name from PHARMACIES FULL OUTER JOIN "
			+ "DERMATOLOGIST_PHARMACY on  DERMATOLOGIST_PHARMACY.pharmacy_id = PHARMACIES.id where DERMATOLOGIST_PHARMACY.dermatologist_id = 10 group by DERMATOLOGIST_PHARMACY.dermatologist_id",nativeQuery=true)
	public List<DermatologistDetailsProjection> getAllByPharmacyIdWithPharmacyList();
	
	//GROUP BY PHARMACIES.name,u.name,u.email 
//	@Query(value = "SELECT PHARMACIES.name as pharmaciesNames,u.name as name,u.email as email,u.id as id  FROM PHARMACIES ,DERMATOLOGIST_PHARMACY,USERS AS u LEFT JOIN USERS AS h"
//			+ " ON u.id != h.id AND u.email = h.email "
//			+ "  WHERE  DERMATOLOGIST_PHARMACY.dermatologist_id = 10 AND PHARMACIES.id = DERMATOLOGIST_PHARMACY.pharmacy_id AND u.id = DERMATOLOGIST_PHARMACY.dermatologist_id", 
//			  nativeQuery = true)
	@Query(value ="SELECT PHARMACIES.name as pharmaciesNames,u.id as id,u.name as name,u.email as email,u.surname as surname FROM PHARMACIES,DERMATOLOGIST_PHARMACY,USERS AS u LEFT JOIN USERS as h on u.id != h.id AND u.email = h.email WHERE PHARMACIES.id = DERMATOLOGIST_PHARMACY.pharmacy_id "
			+ "AND u .id = DERMATOLOGIST_PHARMACY.dermatologist_id ",nativeQuery = true)
	public List<DermatologistDetailsProjection> getDummy();
	
	@Query(value = "SELECT email,u.id, u.name,surname, PHARMACIES.name AS pharmacyName FROM USERS AS u FULL OUTER JOIN "
			+ "DERMATOLOGIST_PHARMACY ON u.id = DERMATOLOGIST_PHARMACY.dermatologist_id FULL OUTER JOIN PHARMACIES"
			+ " ON PHARMACIES.id = DERMATOLOGIST_PHARMACY.pharmacy_id WHERE DERMATOLOGIST_PHARMACY.pharmacy_id != :ID", 
			  nativeQuery = true)
	public List<DermatologistProjection> getAllNotInPharmacy(@Param("ID") Long id);
	
	@Query(value = "SELECT u.name,u.email,u.surname,u.id as dermatologistId,p.id as pharmacyId FROM USERS AS u INNER JOIN DERMATOLOGIST_PHARMACY as dp  ON  u.id = dp.dermatologist_id INNER JOIN PHARMACIES as p ON p.id = dp.pharmacy_id",
			  nativeQuery = true)
	public List<DermatologistPharmacyProjection> getDermatologistPharmacy();
	
    @Modifying
    @Query(value= "delete from dermatologist_pharmacy as dp  where dp.dermatologist_id = :id and dp.pharmacy_id =:idobject",nativeQuery = true)
    void deleteById(@Param("id") Long id,@Param("idobject") Long idobject);
}
