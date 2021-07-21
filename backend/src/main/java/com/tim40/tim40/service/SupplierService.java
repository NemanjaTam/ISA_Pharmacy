package com.tim40.tim40.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tim40.tim40.dto.PurchaseOrderOfferDTO;
import com.tim40.tim40.model.PurchaseOrder;
import com.tim40.tim40.model.PurchaseOrderOffer;
import com.tim40.tim40.model.Supplier;
import com.tim40.tim40.repository.PurchaseOrderOfferRepository;
import com.tim40.tim40.repository.PurchaseOrderRepository;
import com.tim40.tim40.repository.SupplierRepository;

@Service
public class SupplierService implements ISupplierService {

	private SupplierRepository supplierRepository;
    private PurchaseOrderRepository purchaseOrderRepository;
    private PurchaseOrderOfferRepository purchaseOrderOfferRepository;
    
	@Autowired
	public SupplierService(SupplierRepository supplierRepository, PurchaseOrderRepository purchaseOrderRepository, PurchaseOrderOfferRepository purchaseOrderOfferRepository) {
		this.supplierRepository = supplierRepository;
		this.purchaseOrderRepository = purchaseOrderRepository;
		this.purchaseOrderOfferRepository = purchaseOrderOfferRepository;
	}

	@Override
	public ResponseEntity<PurchaseOrderOfferDTO> makePurchaseOrderOffer(PurchaseOrderOfferDTO purchaseOrderOfferDTO) {
		PurchaseOrderOffer purchaseOrderOffer = new PurchaseOrderOffer();
		purchaseOrderOffer.setOffer(purchaseOrderOfferDTO.getOffer());
		
		PurchaseOrder p = purchaseOrderRepository.getById(purchaseOrderOfferDTO.getPurchaseOrderId());
		purchaseOrderOffer.setPurchaseOrder(p);
		
		Supplier s = supplierRepository.getById(purchaseOrderOfferDTO.getSupplierId());
		purchaseOrderOffer.setSupplier(s);
		
		PurchaseOrderOffer created = purchaseOrderOfferRepository.save(purchaseOrderOffer);
		
		return new ResponseEntity<PurchaseOrderOfferDTO>(new PurchaseOrderOfferDTO(created), HttpStatus.OK);
	}

	public ResponseEntity<List<PurchaseOrderOfferDTO>> getPurchaseOrderOffers(Long id) {
		Set<PurchaseOrderOffer> lista = (Set<PurchaseOrderOffer>) supplierRepository.getById(id).getPurchaseOrderOffers();
		List<PurchaseOrderOfferDTO> lista2 = new ArrayList<>();
		
		for(PurchaseOrderOffer p : lista) {
			lista2.add(new PurchaseOrderOfferDTO(p));
		}
		return new ResponseEntity<List<PurchaseOrderOfferDTO>>(lista2, HttpStatus.OK);
	}
	
	
}