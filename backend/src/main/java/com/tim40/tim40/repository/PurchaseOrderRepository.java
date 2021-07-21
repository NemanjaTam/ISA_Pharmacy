package com.tim40.tim40.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tim40.tim40.model.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

}
