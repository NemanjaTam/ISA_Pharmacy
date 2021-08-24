package com.tim40.tim40.dto;

import com.tim40.tim40.model.Offer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AcceptOfferDTO {
 private Long purchaseOrderId;
 private Long offerId;
 private Long pharmacyId;
 
}
