package com.tim40.tim40.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.tim40.tim40.dto.OfferDTO;
import com.tim40.tim40.dto.OfferFilterDTO;
import com.tim40.tim40.dto.PurchaseOrderOfferDTO;
import com.tim40.tim40.model.Pharmacy;
import com.tim40.tim40.model.PurchaseOrder;
import com.tim40.tim40.model.PurchaseOrderOffer;
import com.tim40.tim40.model.QuantityMedication;
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
		purchaseOrderOffer.setStatus("WAITING_FOR_APPROVAL");
		purchaseOrderOffer.setOffer(purchaseOrderOfferDTO.getOffer());
		purchaseOrderOffer.setDeliveryDeadline(purchaseOrderOfferDTO.getDeliveryDeadline());
		
		PurchaseOrder p = purchaseOrderRepository.getById(purchaseOrderOfferDTO.getPurchaseOrderId());
		purchaseOrderOffer.setPurchaseOrder(p);
		
		Supplier s = supplierRepository.getById(purchaseOrderOfferDTO.getSupplierId());
		purchaseOrderOffer.setSupplier(s);
		
		boolean supplierHasAllInStock = true;
		/*for(QuantityMedication qm : p.getQuantityMedications()) {
			int quantityRemaining = qm.getQuantity();
			
			for(Pharmacy pharmacy : s.getPharmacies()) {
				for(QuantityMedication pharmacyQM : pharmacy.getMedicationQuantity()) {
					if(pharmacyQM.getMedication().getId() == qm.getMedication().getId()) {
						quantityRemaining -= pharmacyQM.getQuantity();
					}
				}
			}
			if(quantityRemaining >= 0) {  
				supplierHasAllInStock = false;
				break;
			}
		}*/
		if(supplierHasAllInStock == false) {
			return new ResponseEntity<PurchaseOrderOfferDTO>(new PurchaseOrderOfferDTO(null), HttpStatus.BAD_REQUEST);
		}
		
		PurchaseOrderOffer created = purchaseOrderOfferRepository.save(purchaseOrderOffer);
		
		return new ResponseEntity<PurchaseOrderOfferDTO>(new PurchaseOrderOfferDTO(created), HttpStatus.OK);
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, readOnly = true)
	public ResponseEntity<List<PurchaseOrderOfferDTO>> getPurchaseOrderOffers(Long id) {
		Set<PurchaseOrderOffer> lista = (Set<PurchaseOrderOffer>) supplierRepository.getById(id).getPurchaseOrderOffers();
		List<PurchaseOrderOfferDTO> lista2 = new ArrayList<>();
		
		for(PurchaseOrderOffer p : lista) {
			lista2.add(new PurchaseOrderOfferDTO(p));
		}
		return new ResponseEntity<List<PurchaseOrderOfferDTO>>(lista2, HttpStatus.OK);
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, readOnly = true, noRollbackFor = NullPointerException.class)
	public ResponseEntity<List<OfferDTO>> getSupplierOffers(Long id, OfferFilterDTO offerFilterDTO) {
		Supplier supplier = supplierRepository.getById(id);
		Set<PurchaseOrderOffer> purchaseOrderOffers = new HashSet<>();
		List<OfferDTO> offers = new ArrayList<>();
		
		purchaseOrderOffers = supplier.getPurchaseOrderOffers();
		
		for(PurchaseOrderOffer p : purchaseOrderOffers) {
			String filterStatus = offerFilterDTO.getStatus();
			if(filterStatus.equals("") || filterStatus.equals(p.getStatus())) {
				offers.add(new OfferDTO(p));
			}
		}
		
		return new ResponseEntity<List<OfferDTO>>(offers, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PurchaseOrderOfferDTO> updatePurchaseOrderOffer(PurchaseOrderOfferDTO purchaseOrderOfferDTO) {
		PurchaseOrderOffer poo = purchaseOrderOfferRepository.getById(purchaseOrderOfferDTO.getId());
		
		if(poo == null) {
			return new ResponseEntity<PurchaseOrderOfferDTO>(new PurchaseOrderOfferDTO(null), HttpStatus.BAD_REQUEST);
		}
		
		poo.setStatus("WAITING_FOR_APPROVAL");
		poo.setOffer(purchaseOrderOfferDTO.getOffer());
		poo.setDeliveryDeadline(purchaseOrderOfferDTO.getDeliveryDeadline());
		
		PurchaseOrder p = purchaseOrderRepository.getById(purchaseOrderOfferDTO.getPurchaseOrderId());
		poo.setPurchaseOrder(p);
		
		Supplier s = supplierRepository.getById(purchaseOrderOfferDTO.getSupplierId());
		poo.setSupplier(s);
		
		boolean supplierHasAllInStock = true;
	/*	for(QuantityMedication qm : p.getQuantityMedications()) {
			int quantityRemaining = qm.getQuantity();
			
			for(Pharmacy pharmacy : s.getPharmacies()) {
				for(QuantityMedication pharmacyQM : pharmacy.getMedicationQuantity()) {
					if(pharmacyQM.getMedication().getId() == qm.getMedication().getId()) {
						quantityRemaining -= pharmacyQM.getQuantity();
					}
				}
			}
			if(quantityRemaining >= 0) {  
				supplierHasAllInStock = false;
				break;
			}
		}*/
		if(supplierHasAllInStock == false) {
			return new ResponseEntity<PurchaseOrderOfferDTO>(new PurchaseOrderOfferDTO(null), HttpStatus.BAD_REQUEST);
		}
		
		PurchaseOrderOffer created = purchaseOrderOfferRepository.save(poo);
		
		return new ResponseEntity<PurchaseOrderOfferDTO>(new PurchaseOrderOfferDTO(created), HttpStatus.OK);
	}
	
	
}