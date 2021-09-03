package com.tim40.tim40.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim40.tim40.model.Medication;
import com.tim40.tim40.model.MissingQuantity;

public interface MissingQuantityRepository extends JpaRepository<MissingQuantity, Long> {

}
