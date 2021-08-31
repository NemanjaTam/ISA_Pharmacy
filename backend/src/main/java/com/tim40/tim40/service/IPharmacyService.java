package com.tim40.tim40.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.tim40.tim40.dto.AbsenceDetailedDTO;
import com.tim40.tim40.dto.AcceptOfferDTO;
import com.tim40.tim40.dto.MedicationQuantityDTO;
import com.tim40.tim40.dto.PurchaseOrderDTO;
import com.tim40.tim40.model.Absence;
import com.tim40.tim40.model.Dermatologist;
import com.tim40.tim40.model.Medication;
import com.tim40.tim40.model.Offer;
import com.tim40.tim40.model.Pharmacy;
import com.tim40.tim40.model.PurchaseOrder;

public interface IPharmacyService {
	//meds nikola
	ResponseEntity<List<Medication>> getAllMedications(Long id);
	ResponseEntity<String> isMedicationAvailable(Long pharmacyId, Long medicationId);
	//pharmacy
	public Pharmacy getById(Long pharmacyID);
	public List<String> getAllSubscribers(Long pharmacyID);
	public Long getPharmacyIdByUserId(Long id);
	// offers i medication
	public boolean editMedication(MedicationQuantityDTO dto, Long id);
	public boolean CreatePurchaseOrder(Pharmacy pharmacy, PurchaseOrderDTO dto);
	public boolean CreatePurchaseOrderForNewMedication(Pharmacy pharmacy, PurchaseOrderDTO dto);
	public Set<PurchaseOrder> getAllPurchaseOrders(Long id);
	public List<Offer> acceptOffer(AcceptOfferDTO dto);
	//delete
	public boolean deleteMedication(Long id,Long medicationId);
	public boolean deletePurchaseOrder(Long id,Long pharmacyId);
	//absence
	public Set<AbsenceDetailedDTO> getAllUnapprovedAbsencesByPharmacyId(Long id);
	public Set<Absence> getAllApprovedAbsencesByPharmacyId(Long id);
	public Integer subscribe(Long pharmacyId,Long userId);
	

	
}
