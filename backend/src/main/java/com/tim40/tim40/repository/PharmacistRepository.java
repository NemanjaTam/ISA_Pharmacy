package com.tim40.tim40.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tim40.tim40.model.Consultation;
import com.tim40.tim40.model.Pharmacist;
import com.tim40.tim40.projections.PharmacistDetailsProjection;
import com.tim40.tim40.projections.PharmacistProjection;

public interface PharmacistRepository extends JpaRepository<Pharmacist, Long> { //ne menjati nista
	@Query(value = "SELECT p.name as pharmacyName,u.email,u.id,u.name,u.surname FROM USERS u INNER JOIN PHARMACIES p ON  p.id = u.pharmacy_id WHERE  u.pharmacy_id = :ID AND u.usertype= :type", 
			  nativeQuery = true)
	public List<PharmacistDetailsProjection> getAllByPharmacyId(@Param("ID") Long id,@Param("type") String type);
	
	//@Query(value = "select p.name,p.id,u.email,u.id,u.surname,u.name FROM User u INNER JOIN Pharmacy p WHERE u.Pharmacy = p.id AND u.Pharmacy =:1 AND u.usertype =:type ")
	@Query(value = "SELECT p.name as pharmacyName,u.email,u.id,u.name,u.surname FROM USERS u INNER JOIN PHARMACIES p ON  p.id = u.pharmacy_id WHERE u.usertype= :type", 
			  nativeQuery = true)
	public List<PharmacistDetailsProjection> getAllPharmacist(@Param("type") String type);
	
	@Query("select c from Consultation c where c.pharmacist.id = ?1 and c.patient.id = ?2 and c.isFinished = 'true'")
	Collection<Consultation> getExaminationsForPatientAndPharmacist(Long idDermatologa, Long idPacijenta);
	
	@Query("select c from Consultation c where c.pharmacy.id = ?1 and c.patient.id = ?2 and c.isFinished = 'true'")
	Collection<Consultation> getExaminationsForPatientAndPharmacy(Long idPharm, Long idPacijenta);

}
