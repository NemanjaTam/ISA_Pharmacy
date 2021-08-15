package com.tim40.tim40.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import com.tim40.tim40.model.Period;
import com.tim40.tim40.model.Sale;

public interface SalesRepository extends JpaRepository<Sale, Long>{
	//ne dirati nista
	@Modifying
	@Transactional 
	@Query(value = "INSERT INTO Sales (free_form,is_active,endtime,starttime,pharmacy_admin_id)"
			+ "VALUES (:form,:isActive,:endTime,:startTime,(SELECT id from Users WHERE usertype='PHARMACY_ADMINISTRATOR' AND id=:userId))", 
			  nativeQuery = true)
	public void createSale(@Param("form") String form,@Param("userId") Long userId,@Param("startTime") LocalDate startTime,
			@Param("endTime") LocalDate endTime,@Param("isActive") boolean isActive);
}
