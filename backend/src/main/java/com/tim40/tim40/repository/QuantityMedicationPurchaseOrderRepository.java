package com.tim40.tim40.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim40.tim40.model.QuantityMedicationPurchaseOrder;

public interface QuantityMedicationPurchaseOrderRepository extends JpaRepository<QuantityMedicationPurchaseOrder, Long>{

	public Set<QuantityMedicationPurchaseOrder> findByOrderId(Long orderId);
	
}
