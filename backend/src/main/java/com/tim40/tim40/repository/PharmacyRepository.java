package com.tim40.tim40.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tim40.tim40.model.Pharmacy;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
  @Query(value="select p.id from pharmacies as p,users as u where u.id=:id AND p.id = u.pharmacy_id",nativeQuery = true)
  public Long getPharmacyIdByUserId(@Param("id") Long id);
}
