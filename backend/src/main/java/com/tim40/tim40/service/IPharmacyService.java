package com.tim40.tim40.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.tim40.tim40.dto.AcceptOfferDTO;
import com.tim40.tim40.dto.MedicationQuantityDTO;
import com.tim40.tim40.dto.PurchaseOrderDTO;
import com.tim40.tim40.model.Dermatologist;
import com.tim40.tim40.model.Medication;
import com.tim40.tim40.model.Offer;
import com.tim40.tim40.model.Pharmacy;
import com.tim40.tim40.model.PurchaseOrder;

public interface IPharmacyService {
	ResponseEntity<List<Medication>> getAllMedications(Long id);
	ResponseEntity<String> isMedicationAvailable(Long pharmacyId, Long medicationId);
	public Pharmacy getById(Long pharmacyID);
	public List<String> getAllSubscribers(Long pharmacyID);
	public Long getPharmacyIdByUserId(Long id);
	public boolean editMedication(MedicationQuantityDTO dto, Long id);
	public boolean CreatePurchaseOrder(Pharmacy pharmacy, PurchaseOrderDTO dto);
	public boolean CreatePurchaseOrderForNewMedication(Pharmacy pharmacy, PurchaseOrderDTO dto);
	public Set<PurchaseOrder> getAllPurchaseOrders(Long id);
	public List<Offer> acceptOffer(AcceptOfferDTO dto);
	public boolean deleteMedication(Long id,Long medicationId);

}
