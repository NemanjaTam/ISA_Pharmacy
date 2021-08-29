package com.tim40.tim40.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tim40.tim40.UnixToLocalDateTimeConverter;
import com.tim40.tim40.model.PriceMedication;
import com.tim40.tim40.model.enums.PriceListType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PriceListMedicationDTO {
 private Long id;
 private String startTime;
 private PriceListType status;
 private Set<PriceMedication> medicationPrices;
}
