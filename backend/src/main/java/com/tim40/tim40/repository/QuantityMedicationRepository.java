package com.tim40.tim40.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tim40.tim40.model.QuantityMedication;


public interface QuantityMedicationRepository extends JpaRepository<QuantityMedication, Long>{
    @Modifying
    @Query(value= "delete from medication_quantity as q  where q.medication_id = :id and q.pharmacy_id =:pharmacyId",nativeQuery = true)
    void deleteById(@Param("id") Long id,@Param("pharmacyId") Long pharmacyId);
    
    @Modifying
    @Query(value="update medication_quantity set quantity=:quantity where medication_id=:medicationId and pharmacy_id=:pharmacyId ",nativeQuery= true)
    void update(@Param("quantity") Integer quantity,@Param("pharmacyId") Long pharmacyId,@Param("medicationId") Long medicationId);
    
}
