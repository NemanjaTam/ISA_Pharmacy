package com.tim40.tim40.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tim40.tim40.model.PriceMedication;

public interface PriceMedicationRepository extends JpaRepository<PriceMedication, Long>{

	@Query(value="insert into price_medications(price,medication_id,price_list_medication_id)"
			+ "values(:price,:medicationId,:priceListId) returning id",nativeQuery = true)
	public Integer createPriceList(@Param("medicationId") Long medicationId,@Param("price") double price,@Param("priceListId") Long priceListId);
}
