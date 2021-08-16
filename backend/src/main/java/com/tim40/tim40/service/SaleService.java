package com.tim40.tim40.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tim40.tim40.model.Period;
import com.tim40.tim40.model.Sale;
import com.tim40.tim40.projections.DermatologistProjection;
import com.tim40.tim40.repository.SalesRepository;
@Service
public class SaleService implements ISalesService{

	private SalesRepository salesRepository;


	@Autowired
	public SaleService(SalesRepository salesRepository) {
		this.salesRepository = salesRepository;
	}

	@Override
	public ResponseEntity<String> createSale(String form,Long id,LocalDate startTime,LocalDate endTime,boolean isActive) {
		 this.salesRepository.createSale(form, id, startTime, endTime, isActive);
	
		return new ResponseEntity<String>(HttpStatus.CREATED);
	
	}

}