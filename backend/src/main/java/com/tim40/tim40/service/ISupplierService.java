package com.tim40.tim40.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.tim40.tim40.dto.PurchaseOrderOfferDTO;

public interface ISupplierService {

	ResponseEntity<PurchaseOrderOfferDTO> makePurchaseOrderOffer(PurchaseOrderOfferDTO purchaseOrderOfferDTO);
	ResponseEntity<List<PurchaseOrderOfferDTO>> getPurchaseOrderOffers(Long id);
}
