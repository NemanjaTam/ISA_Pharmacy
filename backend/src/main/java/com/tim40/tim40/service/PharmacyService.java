package com.tim40.tim40.service;

import java.math.BigInteger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tim40.tim40.dto.MedicationQuantityDTO;
import com.tim40.tim40.dto.PharmacyDTO;
import com.tim40.tim40.dto.PurchaseOrderDTO;
import com.tim40.tim40.email.service.MailService;
import com.tim40.tim40.model.Dermatologist;
import com.tim40.tim40.model.Medication;
import com.tim40.tim40.model.Patient;
import com.tim40.tim40.model.Pharmacy;
import com.tim40.tim40.model.PurchaseOrder;
import com.tim40.tim40.model.QuantityMedication;
import com.tim40.tim40.model.User;
import com.tim40.tim40.model.enums.PurchaseOrderStatus;
import com.tim40.tim40.repository.MedicationRepository;
import com.tim40.tim40.repository.PharmacyRepository;



@Service
public class PharmacyService implements IPharmacyService {
	
	private PharmacyRepository pharmacyRepository;
	private MedicationRepository medicationRepository;

	@Autowired
	public PharmacyService(PharmacyRepository pharmacyRepository,MedicationRepository medicationRepository) {
		this.pharmacyRepository = pharmacyRepository;
		this.medicationRepository = medicationRepository;
	}
	
	public PharmacyDTO createPharmacy (PharmacyDTO pharmacyDTO) {
		Pharmacy pharmacy = new Pharmacy(pharmacyDTO.getName(), pharmacyDTO.getAddress());
		Pharmacy createdPharmacy = pharmacyRepository.save(pharmacy); 
		return new PharmacyDTO(createdPharmacy);
	}

	@Override
	public ResponseEntity<List<Medication>> getAllMedications(Long id) {
		Pharmacy pharmacy = pharmacyRepository.findById(id).get();
		List<Medication> medications = new ArrayList<Medication>();
		for(QuantityMedication qm : pharmacy.getMedicationQuantity()) {
			medications.add(qm.getMedication());
		}
		return new ResponseEntity<List<Medication>>(medications,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> isMedicationAvailable(Long pharmacyId, Long medicationId) {
		Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId).get();
		for(QuantityMedication qm : pharmacy.getMedicationQuantity()) {
			if(qm.getQuantity() == 0) {
				return new ResponseEntity<String>("not_available", HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("available", HttpStatus.OK);
	}
//ne menjati
	@Override
	public Pharmacy getById(Long pharmacyID) {
		return pharmacyRepository.getById(pharmacyID);
	}
//ne menjati
	@Override
	public List<String> getAllSubscribers(Long pharmacyID) {
		Pharmacy pharmacy = this.pharmacyRepository.getById(pharmacyID);
		Set<User>subscribers = pharmacy.getSubscribers();
		List<String> emails = new ArrayList<String>();
		for(User sub: subscribers) {
			System.out.println(sub.getEmail());
			emails.add(sub.getEmail());
		}
		return emails;
	}
//ne menjati
	@Override
	public Long getPharmacyIdByUserId(Long id) {
		return this.pharmacyRepository.getPharmacyIdByUserId(id);
		
	}

	public boolean CreatePurchaseOrder(Pharmacy pharmacy, PurchaseOrderDTO dto) {
		Integer integerId = this.pharmacyRepository.createPurchaseOrder( dto.getEndTime(),  dto.getStartTime(), dto.getAdminId(),"CEKA_PONUDE");

        System.out.println(integerId);
        if(integerId > 0) {
		Integer purchaseOrderId = this.pharmacyRepository.insertPurchaseOrderIntoPharmacy(pharmacy.getId(),integerId.longValue());
		
		if(purchaseOrderId > 0) {
			
            List<Medication> medication = pharmacy.getAllMedication();
			
			
			for(MedicationQuantityDTO meds: dto.getMedicationDTO()) {	

				Medication newMedication = new Medication();
				
				
//				meds.getName(),meds.getCode(),meds.getTypeOfMedication(),meds.getStructure(),
//				meds.getContraindications(),meds.getRecommendedIntake(),
//				,meds.getManufacturer()
//				,meds.getMedicationForm(),meds.getPrescriptionRegime()
//				
				
				newMedication.setName(meds.getName());
				newMedication.setCode(meds.getCode());
				newMedication.setTypeOfMedication(meds.getTypeOfMedication());
				newMedication.setStructure(meds.getStructure());
				newMedication.setContraindications(meds.getContraindications());
				newMedication.setRecommendedIntake(meds.getRecommendedIntake());
				newMedication.setDescription(meds.getDescription());
				newMedication.setManufacturer(meds.getManufacturer());
				newMedication.setMedicationForm(meds.getMedicationForm());
				newMedication.setPrescriptionRegime(meds.getPrescriptionRegime());
				Medication created = this.medicationRepository.save(newMedication);
				
				if(!medication.contains(created)) {
					QuantityMedication quantity = new QuantityMedication();
					quantity.setQuantity(0);
					quantity.setMedication(created);
					quantity.setPharmacy(pharmacy);
					pharmacy.getMedicationQuantity().add(quantity);
					
					System.out.println(created.getId());
					Integer id = this.pharmacyRepository.insertIntoQuantityMedicationPurchaseOrder(integerId.longValue(), created.getId());
					this.pharmacyRepository.insertMedicationInPurchaseOrder(purchaseOrderId.longValue(), id.longValue());
				}else {
					Integer id = this.pharmacyRepository.insertIntoQuantityMedicationPurchaseOrder(integerId.longValue(), meds.getId());
					this.pharmacyRepository.insertMedicationInPurchaseOrder(purchaseOrderId.longValue(), id.longValue());
				}
			
			}
			
		}
		else {return false;}
		
		}else {
			return false;
		}

		return true;
		
	}

	
}
