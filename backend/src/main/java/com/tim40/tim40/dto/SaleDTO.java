package com.tim40.tim40.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tim40.tim40.model.Address;
import com.tim40.tim40.model.Email;
import com.tim40.tim40.model.Period;
import com.tim40.tim40.model.Pharmacy;
import com.tim40.tim40.model.PharmacyAdministrator;
import com.tim40.tim40.model.Sale;
import com.tim40.tim40.model.enums.UserType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleDTO {
  private Long adminId;
//  private Long pharmacyId;
  private String form;
//  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate startTime;
//  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate endTime;
  private boolean isActive;
  
  public SaleDTO(Sale sale) {
	  super();
	  this.adminId = sale.getPharmacyAdmin().getId();
//	  this.pharmacyId = sale.getPharmacy().getId();
	  this.form = sale.getFreeForm();
	  this.startTime = sale.getStartTime();
	  this.endTime = sale.getEndTime();
	  this.isActive = sale.isActive();
  }
  
}
