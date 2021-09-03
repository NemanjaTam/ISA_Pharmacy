package com.tim40.tim40.service;

import java.math.BigInteger;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tim40.tim40.dto.AbsenceDetailedDTO;
import com.tim40.tim40.dto.AcceptOfferDTO;
import com.tim40.tim40.dto.MedicationQuantityDTO;
import com.tim40.tim40.dto.PharmacyDTO;
import com.tim40.tim40.dto.PharmacyRatingDTO;
import com.tim40.tim40.dto.PurchaseOrderDTO;
import com.tim40.tim40.dto.PurchaseOrderDetailedDTO;
import com.tim40.tim40.model.enums.AbsenceType;
import com.tim40.tim40.email.service.MailService;
import com.tim40.tim40.model.Absence;
import com.tim40.tim40.model.Appointment;
import com.tim40.tim40.model.Consultation;
import com.tim40.tim40.model.Dermatologist;
import com.tim40.tim40.model.Medication;
import com.tim40.tim40.model.Offer;
import com.tim40.tim40.model.Patient;
import com.tim40.tim40.model.PharmacistRating;
import com.tim40.tim40.model.Pharmacy;
import com.tim40.tim40.model.PharmacyRating;
import com.tim40.tim40.model.PriceListMedication;
import com.tim40.tim40.model.PriceMedication;
import com.tim40.tim40.model.PurchaseOrder;
import com.tim40.tim40.model.QuantityMedication;
import com.tim40.tim40.model.QuantityMedicationPurchaseOrder;
import com.tim40.tim40.model.Reservation;
import com.tim40.tim40.model.User;
import com.tim40.tim40.model.enums.OfferStatus;
import com.tim40.tim40.model.enums.PriceListType;
import com.tim40.tim40.model.enums.PurchaseOrderStatus;
import com.tim40.tim40.repository.AppointmentRepository;
import com.tim40.tim40.repository.ConsultationRepository;
import com.tim40.tim40.repository.MedicationRepository;
import com.tim40.tim40.repository.PharmacyRatingRepository;
import com.tim40.tim40.repository.PharmacyRepository;
import com.tim40.tim40.repository.PriceListMedicationRepository;
import com.tim40.tim40.repository.PurchaseOrderRepository;
import com.tim40.tim40.repository.QuantityMedicationRepository;
import com.tim40.tim40.repository.ReservationRepository;



@Service
public class PharmacyService implements IPharmacyService {
	
	private PharmacyRepository pharmacyRepository;
	private MedicationRepository medicationRepository;
	private QuantityMedicationRepository quantityRepository;
	private ReservationRepository reservationRepository;
	private PurchaseOrderRepository purchaseOrderRepository;
	private PharmacyRatingRepository ratingPharmacyRepository;
	private AppointmentRepository appointmentRepository;
	private ConsultationRepository consultationRepository;
	private PriceListMedicationRepository priceListRepository;

	@Autowired
	public PharmacyService(PharmacyRepository pharmacyRepository,MedicationRepository medicationRepository,QuantityMedicationRepository quantityRepository,ReservationRepository reservationRepository,
			 PurchaseOrderRepository purchaseOrderRepository,PharmacyRatingRepository ratingPharmacyRepository,AppointmentRepository appointmentRepository
			 ,ConsultationRepository consultationRepository) {
		this.pharmacyRepository = pharmacyRepository;
		this.medicationRepository = medicationRepository;
		this.quantityRepository = quantityRepository;
		this.reservationRepository = reservationRepository;
		this.purchaseOrderRepository = purchaseOrderRepository;
		this.ratingPharmacyRepository = ratingPharmacyRepository;
		this.appointmentRepository = appointmentRepository;
		this.consultationRepository = consultationRepository;
	}
	
	public PharmacyDTO createPharmacy (PharmacyDTO pharmacyDTO) {
		Pharmacy pharmacy = new Pharmacy(pharmacyDTO.getName(), pharmacyDTO.getAddress());
		Pharmacy createdPharmacy = pharmacyRepository.save(pharmacy); 
		return new PharmacyDTO(createdPharmacy);
	}
	
	//ne menjati
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
	
	
	//ne menjati
	@Override
	public boolean CreatePurchaseOrder(Pharmacy pharmacy, PurchaseOrderDTO dto) {
	Integer integerId = this.pharmacyRepository.createPurchaseOrder( dto.getEndTime(),  dto.getStartTime(), dto.getAdminId(),"CEKA_PONUDE",pharmacy.getId());
	
//		if(integerId > 0) {
		 PurchaseOrder purchaseOrder = new PurchaseOrder();
	
            List<Medication> medication = pharmacy.getAllMedication();
			for(MedicationQuantityDTO meds: dto.getMedicationDTO()) {	//brufen
				for (Medication medication2 : medication) {
					if(medication2.getId() == meds.getId()){
							System.out.println(medication2.getId());
							System.out.println(medication2.getName());
							System.out.println("*************\n");
							System.out.println(meds.getId());
							System.out.println(meds.getId());
							
							Integer id = this.pharmacyRepository.insertIntoQuantityMedicationPurchaseOrder(meds.getQuantity().intValue(), meds.getId());
							this.pharmacyRepository.insertMedicationInPurchaseOrder(integerId.longValue(), id.longValue());
							System.out.println("usao u drugi if!");}			
			}//ovo se svakako izvrsi 
		}
//	}else{
//		return false;
//		}
		return true;
		
	}
	
	
	//ne menjati
	@Override
	public boolean CreatePurchaseOrderForNewMedication(Pharmacy pharmacy, PurchaseOrderDTO dto) {
		Integer integerId = this.pharmacyRepository.createPurchaseOrder( dto.getEndTime(),  dto.getStartTime(), dto.getAdminId(),"CEKA_PONUDE",pharmacy.getId());
		if(integerId > 0) {
            List<Medication> medication = pharmacy.getAllMedication();
			for(MedicationQuantityDTO meds: dto.getMedicationDTO()) {
				Medication newMedication = new Medication();	
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
				QuantityMedication quantity = new QuantityMedication();
				quantity.setQuantity(0);
				quantity.setMedication(created);
				quantity.setPharmacy(pharmacy);
				pharmacy.getMedicationQuantity().add(quantity);
				
	
				Integer id = this.pharmacyRepository.insertIntoQuantityMedicationPurchaseOrder(meds.getQuantity().intValue(), created.getId());
				this.pharmacyRepository.insertMedicationInPurchaseOrder(integerId.longValue(), id.longValue());//brufen
}
					
			}else {return false;}
		return true;
		
	}
	@Override
	public Set<PurchaseOrder> getAllPurchaseOrders(Long id){
		Set<PurchaseOrder> purchaseOrdersDTO = new HashSet<PurchaseOrder>();
		Pharmacy pharmacy = this.pharmacyRepository.getById(id);
		for (PurchaseOrder purchaseOrder : pharmacy.getPurchaseOrders()) {
			if(purchaseOrder.getPurchaseOrderStatus().equals(PurchaseOrderStatus.CEKA_PONUDE) || purchaseOrder.getPurchaseOrderStatus().equals(PurchaseOrderStatus.OBRADJENA)) {
				purchaseOrdersDTO.add(purchaseOrder);
			}
		}
		
		return purchaseOrdersDTO;
	}
	
	@Override
	public List<Offer> acceptOffer(AcceptOfferDTO dto){
		Pharmacy pharmacy = this.pharmacyRepository.getById(dto.getPharmacyId());
//		List<QuantityMedicationPurchaseOrder> qmpo = this.
		List<Offer> offers = new ArrayList<Offer>();
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		for (PurchaseOrder purchaseOrders : pharmacy.getPurchaseOrders()) {
			if(purchaseOrders.getId().equals(dto.getPurchaseOrderId())) {
				 purchaseOrder.setAdminPharmacy(purchaseOrders.getAdminPharmacy());
				 purchaseOrder.setEndTime(purchaseOrders.getEndTime());
				 purchaseOrder.setStartTime(purchaseOrders.getStartTime());
				 purchaseOrder.setId(purchaseOrders.getId());
				 purchaseOrders.setPurchaseOrderStatus(PurchaseOrderStatus.OBRADJENA);
				 purchaseOrder.setOffers(purchaseOrders.getOffers());
				 purchaseOrder.setPharmacy(pharmacy);
				 purchaseOrder.setPurchaseOrderStatus(purchaseOrders.getPurchaseOrderStatus());
			     offers = purchaseOrders.getOffers();
				
				}
			}	
		for (Offer offer : offers) {
			
			if(offer.getId().equals(dto.getOfferId())) {
				
				offer.setStatus(OfferStatus.PRIHVACENA);
			}else {
				offer.setStatus(OfferStatus.ODBIJENA);
			}
		}
		int quantity = 0;
		for (QuantityMedication qm : pharmacy.getMedicationQuantity()) {
			for (QuantityMedicationPurchaseOrder pm : dto.getQuantityMedicationPurchaseOrder()) {
				if(qm.getMedication().getId().equals(pm.getMedication().getId())) {
					
					quantity = qm.getQuantity();
					quantity = quantity + pm.getQuantity();
					this.quantityRepository.update(quantity,pharmacy.getId(),pm.getMedication().getId());
                    System.out.println("QUANTITY:"  + quantity);
                    System.out.println("IN BASE:" + qm.getQuantity());
                    System.out.println("IN DTO:" + pm.getQuantity());
				}

			}

		}
        
		this.pharmacyRepository.save(pharmacy);
		
		return offers;
	}
	//ne menjati,koristi se
	public void setQuantityFromAcceptedOffer(Pharmacy pharmacy,PurchaseOrder purchaseOrder) {
	    int quantity = 0;
		for (QuantityMedication qm : pharmacy.getMedicationQuantity()) {
			for (QuantityMedicationPurchaseOrder pm : purchaseOrder.getQuantityMedicationsPurchase()) {
				if(qm.getMedication().getId().equals(pm.getMedication().getId())) {
//					qmnew.setId(qm.getId());
//					qmnew.setMedication(qm.getMedication());
//					qmnew.setPharmacy(qm.getPharmacy());
//					qmnew.setQuantity(qm.getQuantity() + pm.getQuantity());
//				    
					quantity = qm.getQuantity();
					quantity = quantity + pm.getQuantity();
					this.quantityRepository.update(quantity,pharmacy.getId(),pm.getMedication().getId());
//					newQuantities.add(qmnew);
				}
			}
			
		}
		
		
	}
	//ne menjati
	@Override
	public boolean deleteMedication(Long id,Long medicationId) {
		Pharmacy pharmacy = pharmacyRepository.getById(id);
//		List<Reservation> reservations = this.reservationRepository.findAll();
//		List<Reservation> reservationsFilteredById = new ArrayList<Reservation>();
//		for (Reservation reservation : reservations) {
//			if(reservation.getPharmacy().getId().equals(pharmacy.getId())) {
//				reservationsFilteredById.add(reservation);
//			}
//		}
//		for (Reservation reservation : reservationsFilteredById) {
//			if(reservation.getMedication().getId().equals(medicationId)) {
//				if(!reservation.isDone()) {
//					return false;
//				}
//			}
//			
//			
//		}
		this.quantityRepository.deleteById(medicationId, pharmacy.getId());		
		return true;
		
	}
	
	public boolean isReserved(Long id,Long medicationId) {
		boolean exist = false;
		Pharmacy pharmacy = pharmacyRepository.getById(id);
		List<Reservation> reservations = this.reservationRepository.findAll();
		List<Reservation> reservationsFilteredById = new ArrayList<Reservation>();
		for (Reservation reservation : reservations) {
			if(reservation.getPharmacy().getId().equals(pharmacy.getId())) {
				reservationsFilteredById.add(reservation);
			}
		}
		for (Reservation reservation : reservationsFilteredById) {
			if(reservation.getMedication().getId().equals(medicationId)) {
				if(!reservation.isDone()) {
					exist = true;
				}
			}
		}	
		return exist;	
		
	}
	
	
//ne menjati
	@Override
	@Transactional(propagation =  Propagation.REQUIRES_NEW,isolation = Isolation.REPEATABLE_READ,rollbackFor = Exception.class,readOnly = false)
	public Medication editMedication(MedicationQuantityDTO dto, Long id) {
		Pharmacy pharmacy = this.pharmacyRepository.getById(id);
		
		Medication med = new Medication();
		boolean found = false;
		for(QuantityMedication qm : pharmacy.getMedicationQuantity()) {
			if(qm.getMedication().getId().equals(dto.getId())) {
			 med = this.medicationRepository.getById(qm.getMedication().getId());
			 if(dto.getVersion() != med.getVersion()) {
				   throw new OptimisticLockException();
				   
			 }
				
				med.setId(qm.getMedication().getId());
				med.setCode(dto.getCode());
				med.setName(dto.getName());
				med.setDescription(dto.getDescription());
				med.setManufacturer(dto.getManufacturer());
				med.setMedicationForm(dto.getMedicationForm());
				med.setTypeOfMedication(dto.getTypeOfMedication());
				med.setPrescriptionRegime(dto.getPrescriptionRegime());
				med.setContraindications(dto.getContraindications());
				med.setRecommendedIntake(dto.getRecommendedIntake());
			
			
			System.out.println(med.getName());
//				qm.getMedication().setCode(dto.getCode());
//				qm.getMedication().setName(dto.getName());
//				qm.getMedication().setDescription(dto.getDescription());
//				qm.getMedication().setManufacturer(dto.getManufacturer());
//				qm.getMedication().setMedicationForm(dto.getMedicationForm());
//				qm.getMedication().setTypeOfMedication(dto.getTypeOfMedication());
//				qm.getMedication().setPrescriptionRegime(dto.getPrescriptionRegime());
//				qm.getMedication().setContraindications(dto.getContraindications());
//				this.quantityRepository.save(qm);
				this.medicationRepository.save(med);
				found = true;
			}
		}
		
//	this.pharmacyRepository.save(pharmacy);
		return med;
	}

	@Override
	public boolean deletePurchaseOrder(Long id, Long pharmacyId) {
		Pharmacy pharmacy = this.getById(pharmacyId);
		for(PurchaseOrder po : pharmacy.getPurchaseOrders()) {
			if(po.getId().equals(id)) {
				if(po.getOffers().size() > 1) {
					return false;
					
				}
			}
		}
		this.purchaseOrderRepository.deleteOrderedQuantity(id);
		this.purchaseOrderRepository.deleteById(id, pharmacyId);
		return true;
	}

	@Override
	public Set<AbsenceDetailedDTO> getAllUnapprovedAbsencesByPharmacyId(Long id) {
		Set<AbsenceDetailedDTO> unapproved = new HashSet<AbsenceDetailedDTO>();
		
		Pharmacy pharmacy = this.pharmacyRepository.getById(id);
		for (Absence absence : pharmacy.getAbsences()) {
			if(absence.getType().equals(AbsenceType.PROCESSING)) {
				AbsenceDetailedDTO dto = new AbsenceDetailedDTO();
				dto.setId(absence.getId());
				dto.setApproved(absence.isApproved());
				dto.setFinished(absence.isFinished());
				dto.setName(absence.getUser().getName());
				dto.setSurname(absence.getUser().getSurname());
				dto.setType(absence.getType());
				dto.setEndTime(absence.getEndTime());
				dto.setStartTime(absence.getStartTime());
				unapproved.add(dto);
			}
		}
		return unapproved;
	}

	@Override
	public Set<Absence> getAllApprovedAbsencesByPharmacyId(Long id) {
		Set<Absence> approved = new HashSet<Absence>();
		Pharmacy pharmacy = this.pharmacyRepository.getById(id);
		for (Absence absence : pharmacy.getAbsences()) {
			if(absence.getType().equals(AbsenceType.APPROVED)) {
				approved.add(absence);
			}
		}
		return approved;
	}
   
	public List<PharmacyRatingDTO> getPharmacyRatings(Long id) {
		Pharmacy pharmacy = this.pharmacyRepository.getById(id);
		List<PharmacyRatingDTO> list = new ArrayList<PharmacyRatingDTO>();
		List<PharmacyRating> ratings = this.ratingPharmacyRepository.findAll();
		double avg = 0;
		int rated = 0;
		int size = 0;
		for (PharmacyRating pharmacyRating : ratings) {
			if(pharmacyRating.getPharmacy().getId().equals(pharmacy.getId())) {
				size = size + 1;
				rated = rated + pharmacyRating.getRating();
			}
		}
		avg = Double.valueOf(rated) / Double.valueOf(size);
		PharmacyRatingDTO dto = new PharmacyRatingDTO(pharmacy.getName(), avg);
		list.add(dto);
		return list;
	}

	@Override
	public Integer subscribe( Long userId,Long pharmacyId) {
		Integer id = this.pharmacyRepository.subscribe(userId, pharmacyId);
		return id;
	}
	
	public List<Appointment> getAllAppointmentsByPharmacyId(Long id,LocalDateTime start,LocalDateTime end) {
		List<Appointment> appointments = this.appointmentRepository.findAll();
		List<Appointment> finishedAppointments = new ArrayList<Appointment>();
		for (Appointment appointment : appointments) {
			if(appointment.isFinished() && appointment.getPharmacy().getId().equals(id)) {
				if(appointment.getPeriod().getStartTime().isAfter(start)) {
				finishedAppointments.add(appointment);}
			}
		}
		return finishedAppointments;
	}
	
	
	public List<PriceListMedication> getActivePriceList(Long pharmacyId,LocalDateTime start,LocalDateTime end) {
	    List<PriceListMedication> list = this.priceListRepository.getAllByPharmacyId(pharmacyId);
	    if(list.size() > 0) {
	    	System.out.println("DOBRO JE");
	    }
	    List<PriceListMedication> active = new ArrayList<PriceListMedication>();
	    for (PriceListMedication priceListMedication : list) {
			if((priceListMedication.getStartTime().isAfter(start))) {
				active.add(priceListMedication);
				
			}
		}
		return active;
	}
	public List<Reservation> getAllReservationsByPharmacyId(Long id,LocalDateTime start,LocalDateTime end) {
		List<Reservation> appointments = this.reservationRepository.findAll();
	
		List<Reservation> finishedAppointments = new ArrayList<Reservation>();
		List<Double> prices = new ArrayList<Double>();
		for (Reservation appointment : appointments) {
			if(appointment.isDone() && appointment.getPharmacy().getId().equals(id)) {
				if((appointment.getPeriod().getStartTime().isAfter(start) || appointment.getPeriod().getStartTime().equals(start)) &&
						(appointment.getPeriod().getEndTime().isBefore(end) || appointment.getPeriod().getEndTime().equals(end))) {
				finishedAppointments.add(appointment);
				}
			}
		}
		
		return finishedAppointments;
	}
	public Map<String,Integer> getPharmacyIncome(Long id,LocalDateTime start,LocalDateTime end) {
		List<Appointment> finishedAppointments = getAllAppointmentsByPharmacyId(id,start,end);
		List<Reservation> finishedReservations = getAllReservationsByPharmacyId(id, start, end);
//		List<PriceListMedication>  priceLists= getActivePriceList(id, start, end);
		LocalDateTime now = LocalDateTime.now();
		int value = 1;
		Map<String, Integer> map = new HashMap<>();
	
	
	    for (Appointment appointment : finishedAppointments) {		
				if(map.containsKey(appointment.getPeriod().getEndTime().toString())) {
					map.put(appointment.getPeriod().getEndTime().toString(),map.get(appointment.getPeriod().getEndTime().toString()) + appointment.getPrice());
					
				}else {
					map.put(appointment.getPeriod().getEndTime().toString(),appointment.getPrice());
				}	
		}
	    
//	    for (PriceListMedication priceListMedication : priceLists) {
//	    	for (PriceMedication price : priceListMedication.getMedicationPrices()) {
//	    		for (Reservation reserv : finishedReservations) {
//	   			 if(reserv.getMedication().getId() == price.getMedication().getId() && reserv.getPeriod().getStartTime().isAfter(priceListMedication.getStartTime())) {
//	   				
//	   				if(map.containsKey(reserv.getPeriod().getEndTime().toString())) {
//	   				 map.put(reserv.getPeriod().getStartTime(), (int) (map.get(reserv.getPeriod().getStartTime().toString()) + price.getPrice()));
//						
//					}else {
//						map.put(reserv.getPeriod().getEndTime(),(int)(price.getPrice()));
//					}	
//	   				 
//	   				
//	   			 }
//	   			
//	   		}
//			}
//		 
//	    }
	    

		return map;
	}
}
