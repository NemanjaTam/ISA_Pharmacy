package com.tim40.tim40.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tim40.tim40.model.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
	//ne dirati nista
    @Modifying
    @Query(value= "delete from purchase_order_list as po  where po.id = :id and po.pharmacy_id =:pharmacyId",nativeQuery = true)
    void deleteById(@Param("id") Long id,@Param("pharmacyId") Long pharmacyId);
    @Modifying
    @Query(value="delete from purchase_order_list_quantity_medications_purchase as po where po.purchase_order_id = :id",nativeQuery = true)
    void deleteOrderedQuantity(@Param("id") Long id);

}
