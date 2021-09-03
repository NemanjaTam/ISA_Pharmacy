package com.tim40.tim40.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tim40.tim40.dto.OfferDTO;
import com.tim40.tim40.dto.OfferFilterDTO;
import com.tim40.tim40.dto.PurchaseOrderOfferDTO;
import com.tim40.tim40.model.Pharmacy;
import com.tim40.tim40.model.PurchaseOrder;
import com.tim40.tim40.model.PurchaseOrderOffer;
import com.tim40.tim40.model.QuantityMedication;
import com.tim40.tim40.model.QuantityMedicationPurchaseOrder;
import com.tim40.tim40.model.Supplier;
import com.tim40.tim40.model.enums.PurchaseOrderStatus;
import com.tim40.tim40.repository.PurchaseOrderOfferRepository;
import com.tim40.tim40.repository.PurchaseOrderRepository;
import com.tim40.tim40.repository.QuantityMedicationPurchaseOrderRepository;
import com.tim40.tim40.repository.SupplierRepository;

@Service
public class SupplierService implements ISupplierService {

	private SupplierRepository supplierRepository;
    private PurchaseOrderRepository purchaseOrderRepository;
    private PurchaseOrderOfferRepository purchaseOrderOfferRepository;
    private QuantityMedicationPurchaseOrderRepository medQuantRepository;

	@Autowired
	public SupplierService(SupplierRepository supplierRepository, PurchaseOrderRepository purchaseOrderRepository, PurchaseOrderOfferRepository purchaseOrderOfferRepository,
			QuantityMedicationPurchaseOrderRepository medQuantRepository) {
		this.supplierRepository = supplierRepository;
		this.purchaseOrderRepository = purchaseOrderRepository;
		this.purchaseOrderOfferRepository = purchaseOrderOfferRepository;
		this.medQuantRepository = medQuantRepository;
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
		for(QuantityMedication qm : p.getQuantityMedications()) {
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
		}
		if(supplierHasAllInStock == false) {
			return new ResponseEntity<PurchaseOrderOfferDTO>(new PurchaseOrderOfferDTO(null), HttpStatus.BAD_REQUEST);
		}

		PurchaseOrderOffer created = purchaseOrderOfferRepository.save(purchaseOrderOffer);

		return new ResponseEntity<PurchaseOrderOfferDTO>(new PurchaseOrderOfferDTO(created), HttpStatus.OK);
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
		for(QuantityMedication qm : p.getQuantityMedications()) {
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
		}
		if(supplierHasAllInStock == false) {
			return new ResponseEntity<PurchaseOrderOfferDTO>(new PurchaseOrderOfferDTO(null), HttpStatus.BAD_REQUEST);
		}

		PurchaseOrderOffer created = purchaseOrderOfferRepository.save(poo);

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

	public ResponseEntity<List<OfferDTO>> getSupplierOffers(Long id, OfferFilterDTO offerFilterDTO) {
		Supplier supplier = supplierRepository.getById(id);
		Set<PurchaseOrderOffer> purchaseOrderOffers = new HashSet<>();
		List<OfferDTO> offers = new ArrayList<>();

		if(supplier == null) {
			return new ResponseEntity<List<OfferDTO>>(offers, HttpStatus.BAD_REQUEST);
		}
		purchaseOrderOffers = supplier.getPurchaseOrderOffers();

		for(PurchaseOrderOffer p : purchaseOrderOffers) {
			String filterStatus = offerFilterDTO.getStatus();
			if(filterStatus.equals("") || filterStatus.equals(p.getStatus())) {
				offers.add(new OfferDTO(p));
			}
		}

		return new ResponseEntity<List<OfferDTO>>(offers, HttpStatus.OK);
	}
	
	public ResponseEntity<List<PurchaseOrder>> getOrdersOnHold(){
		List<PurchaseOrder> orders = purchaseOrderRepository.findByPurchaseOrderStatus(PurchaseOrderStatus.CEKA_PONUDE);
		for (PurchaseOrder purchaseOrder : orders) {
			Set<QuantityMedication> listOfMed = new HashSet<QuantityMedication>();
			Set<QuantityMedicationPurchaseOrder> listOfMed2 = medQuantRepository.findByOrderId(purchaseOrder.getId());
			for (QuantityMedicationPurchaseOrder medQuant : listOfMed2) {
				QuantityMedication qm = new QuantityMedication(medQuant.getQuantity(), medQuant.getMedication());
				listOfMed.add(qm);
			}
			purchaseOrder.setQuantityMedications(listOfMed);
		}
		List<PurchaseOrder> retVal = orders.stream().filter(po -> po.getPeriod().getEndTime().isBefore(LocalDateTime.now())).collect(Collectors.toList());
		return new ResponseEntity<List<PurchaseOrder>>(retVal, HttpStatus.OK);
	}
	public ResponseEntity<List<QuantityMedication>> getPairs(Long id){
		List<QuantityMedication> retVal = new ArrayList<QuantityMedication>();
		Set<QuantityMedicationPurchaseOrder> listOfMed2 = medQuantRepository.findByOrderId(id);
		for (QuantityMedicationPurchaseOrder medQuant : listOfMed2) {
			QuantityMedication qm = new QuantityMedication(medQuant.getQuantity(), medQuant.getMedication());
			retVal.add(qm);
		}
		return new ResponseEntity<List<QuantityMedication>>(retVal, HttpStatus.OK);
	}

	public ResponseEntity<List<PurchaseOrderOfferDTO>> getPurchaseOrderOffersFiltered(List<String> optionsSelected,
			Long id) {
		Set<PurchaseOrderOffer> lista = (Set<PurchaseOrderOffer>) supplierRepository.getById(id).getPurchaseOrderOffers();
		List<PurchaseOrderOfferDTO> lista2 = new ArrayList<>();

		for(PurchaseOrderOffer p : lista) {
			if(optionsSelected.contains(p.getStatus()))
				lista2.add(new PurchaseOrderOfferDTO(p));
		}
		return new ResponseEntity<List<PurchaseOrderOfferDTO>>(lista2, HttpStatus.OK);
		
	}

}