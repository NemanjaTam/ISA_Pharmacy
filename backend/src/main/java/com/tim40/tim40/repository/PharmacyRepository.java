package com.tim40.tim40.repository;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tim40.tim40.model.Pharmacy;
import com.tim40.tim40.model.enums.PurchaseOrderStatus;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
//ne menjati
  @Query(value="select p.id from pharmacies as p,users as u where u.id=:id AND p.id = u.pharmacy_id",nativeQuery = true)
  public Long getPharmacyIdByUserId(@Param("id") Long id);
  
  
  
// METODE ZA PURCHASE ORDER - NE MENJATI ***
  @Query(value="INSERT INTO purchase_order_list(endtime,purchase_status,starttime,pharmacy_administrator_id)"
  		+ " values(:endTime,:status,:startTime,:adminId) RETURNING id",nativeQuery= true)
  public Integer createPurchaseOrder(@Param("endTime") LocalDate endTime,@Param("startTime") LocalDate startTime,@Param("adminId") Long adminId,@Param("status")String status);
  
  @Query( value = "insert into pharmacies_purchase_orders(pharmacy_id,purchase_orders_id)"
  		+ "values(:pharmaycId,:purchaseOrderId) returning purchase_orders_id",nativeQuery = true)
  public Integer insertPurchaseOrderIntoPharmacy(@Param("pharmaycId") Long pharmaycId,@Param("purchaseOrderId") Long purchaseOrderId);

  @Query(value="insert into medication_quantity_of_purchase_order(quantity,medication_id) "
  		+ "values(:id,:medId) RETURNING id",nativeQuery= true)
  public Integer insertIntoQuantityMedicationPurchaseOrder(@Param("id") Long id,@Param("medId") Long medId); //ovde postavljam medicatione
  @Modifying
  @Transactional
  @Query(value="insert into purchase_order_list_quantity_medications_purchase(purchase_order_id,quantity_medications_purchase_id)"
  		+ "values(:purchaseId,:id)",nativeQuery = true)
  public void insertMedicationInPurchaseOrder(@Param("purchaseId") Long purchaseId,@Param("id") Long id); //ovde konacno stavljam lekove u purchase order tj povezujem ih
  //***
}


