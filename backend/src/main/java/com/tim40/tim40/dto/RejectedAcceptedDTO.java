package com.tim40.tim40.dto;

import java.util.List;
import java.util.Set;

import com.tim40.tim40.model.Offer;
import com.tim40.tim40.model.PurchaseOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RejectedAcceptedDTO {
 private List<Offer> offers;
 private Set<PurchaseOrder> purchaseOrder;
}
