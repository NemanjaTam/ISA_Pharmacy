package com.tim40.tim40.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tim40.tim40.model.PriceListMedication;

public interface PriceListMedicationRepository extends JpaRepository<PriceListMedication, Long>{
	
	@Query(value="select * from price_list_medications where pharmacy_id=:id",nativeQuery = true)
	public List<PriceListMedication> getAllByPharmacyId(@Param("id") Long id);

}
