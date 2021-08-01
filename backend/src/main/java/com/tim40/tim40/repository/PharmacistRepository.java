package com.tim40.tim40.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tim40.tim40.model.Pharmacist;
import com.tim40.tim40.projections.PharmacistProjection;

public interface PharmacistRepository extends JpaRepository<Pharmacist, Long> {
	@Query(value = "SELECT email,id,name,surname FROM USERS WHERE USERS.pharmacy_id = :ID AND USERS.usertype= :type", 
			  nativeQuery = true)
//	@Query(value = "SELECT * FROM User u WHERE u.Pharmacy.Id = :ID AND p.Id = u.Pharmacy.Id", 
//	  nativeQuery = true)
	public List<PharmacistProjection> getAllByPharmacyId(@Param("ID") Long id,@Param("type") String type);
  
}
