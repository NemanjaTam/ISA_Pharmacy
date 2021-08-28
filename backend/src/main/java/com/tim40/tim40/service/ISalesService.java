package com.tim40.tim40.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;

import com.tim40.tim40.model.Period;
import com.tim40.tim40.model.Sale;

public interface ISalesService {
  ResponseEntity<String> createSale(String form,Long id,LocalDate startTime,LocalDate endTime,boolean isActive);
}
