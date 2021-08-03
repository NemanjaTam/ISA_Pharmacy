package com.tim40.tim40.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;


import com.tim40.tim40.model.PharmacyAdministrator;
import com.tim40.tim40.projections.PharmacyProjection;

public interface PharmacyAdministratorRepository extends JpaRepository<PharmacyAdministrator, Long>{
	
    PharmacyAdministrator findById(long id);
    PharmacyAdministrator findByEmail(String email);
    List<PharmacyAdministrator> findAll();
    
    @Query(value="SELECT USERS.name, PHARMACIES.id from USERS, PHARMACIES WHERE USERS.id = :ID AND USERS.pharmacy_id = PHARMACIES.id",nativeQuery = true)
    public PharmacyProjection getPharmacy(@Param("ID") Long id);
    @Modifying
    PharmacyAdministrator save(PharmacyAdministrator pharmacyAdmin);
}
