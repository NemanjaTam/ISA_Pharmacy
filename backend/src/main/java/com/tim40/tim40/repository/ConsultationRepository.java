package com.tim40.tim40.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tim40.tim40.model.Consultation;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    @Modifying
    @Query(value= "delete from users as u  where u.email =:email and u.pharmacy_id=:pharmacyId and u.usertype='PHARMACIST'",nativeQuery = true)
    void deleteByEmail(@Param("pharmacyId") Long pharmacyId,@Param("email") String email);
}
