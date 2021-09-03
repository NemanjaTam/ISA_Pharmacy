package com.tim40.tim40.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tim40.tim40.dto.DermatologistRatingDTO;
import com.tim40.tim40.dto.PharmacistRatingDTO;
import com.tim40.tim40.model.Absence;
import com.tim40.tim40.model.Consultation;
import com.tim40.tim40.model.Dermatologist;
import com.tim40.tim40.model.DermatologistRating;
import com.tim40.tim40.model.Patient;
import com.tim40.tim40.model.Pharmacist;
import com.tim40.tim40.model.PharmacistRating;
import com.tim40.tim40.model.Pharmacy;
import com.tim40.tim40.model.User;
import com.tim40.tim40.model.WorkDay;
import com.tim40.tim40.projections.DermatologistProjection;
import com.tim40.tim40.projections.PharmacistDetailsProjection;
import com.tim40.tim40.projections.PharmacistProjection;
import com.tim40.tim40.repository.AbsenceRepository;
import com.tim40.tim40.repository.ConsultationRepository;
import com.tim40.tim40.repository.PharmacistRatingRepository;
import com.tim40.tim40.repository.PharmacistRepository;
import com.tim40.tim40.repository.PharmacyRepository;
import com.tim40.tim40.repository.UserRepository;
import com.tim40.tim40.repository.WorkDayRepository;

@Service
public class PharmacistService implements IPharmacistService {

	private PharmacistRepository pharmacistRepository;
	private PharmacistRatingRepository pharmacistRatingRepository;
	private UserRepository userRepository;
	private PharmacyRepository pharmacyRepository;
	private AbsenceRepository absenceRepository;
	private ConsultationRepository consultationRepository;
	private WorkDayRepository workdayRepository;


	@Autowired
	public PharmacistService(PharmacistRepository pharmacistRepository,PharmacistRatingRepository pharmacistRatingRepository,
			UserRepository userRepository,PharmacyRepository pharmacyRepository,AbsenceRepository absenceRepository,
			ConsultationRepository consultationRepository,WorkDayRepository workdayRepository) {
		this.pharmacistRepository = pharmacistRepository;
		this.pharmacistRatingRepository = pharmacistRatingRepository;
		this.userRepository = userRepository;
		this.pharmacyRepository = pharmacyRepository;
		this.absenceRepository = absenceRepository;
		this.consultationRepository = consultationRepository;
		this.workdayRepository = workdayRepository;

	}

	@Override
	public ResponseEntity<Set<Patient>> getAllPatients(Long id) {
		Pharmacist pharmacist = pharmacistRepository.findById(id).get();
		return new ResponseEntity<Set<Patient>>(pharmacist.getAllPatients(), HttpStatus.OK);
	}

  //preko idja farmacije: ne menjati
	@Override
	public ResponseEntity<List<PharmacistDetailsProjection>> getAllPharamcistsById(Long ID) {
		return new ResponseEntity<List<PharmacistDetailsProjection>>(pharmacistRepository.getAllByPharmacyId(ID,"PHARMACIST"), HttpStatus.OK);
	}
	//vraca sve farmaceute: ne menjati
	@Override
	public ResponseEntity<List<PharmacistDetailsProjection>> getAllPharamcists() {
		return new ResponseEntity<List<PharmacistDetailsProjection>>(pharmacistRepository.getAllPharmacist("PHARMACIST"), HttpStatus.OK);
	}
	
	
	  //pomocna metoda: ne menjati
	@Override
	public ResponseEntity<List<PharmacistDetailsProjection>> getAllPharmacistData(Long ID) {
		return new ResponseEntity<List<PharmacistDetailsProjection>>(pharmacistRepository.getAllByPharmacyId(ID,"PHARMACIST"), HttpStatus.OK);
	}
	  //pomocna metoda: ne menjati
	@Override
	public Long getPharmacy(Long id) {
		Pharmacist pharmacist = pharmacistRepository.findById(id).get();
		return pharmacist.getPharmacy().getId();
//		return null;
	}

	@Override
	public ResponseEntity<List<PharmacistDetailsProjection>> getAllPharamcistsNEW(Long ID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public List<PharmacistRatingDTO> getPharmacistRatings(Long id,String type){
		List<PharmacistRatingDTO> dermatologistsWithRatings = new ArrayList<PharmacistRatingDTO>();
		List<PharmacistRating> ratings = this.pharmacistRatingRepository.findAll();
		for (PharmacistDetailsProjection dermatologistProjection : this.pharmacistRepository.getAllByPharmacyId(id, type)) {
			int rated = 0;
			int size = 0;
			for (PharmacistRating dermatologistRating : ratings) {
				if(dermatologistRating.getPharmacist().getId() == dermatologistProjection.getId()) {
					
					rated = rated + dermatologistRating.getRating();
				
					size = size + 1;				
				}
			}
	    System.out.println(rated);
		double avg = Double.valueOf(rated) / Double.valueOf(size);

		PharmacistRatingDTO dto = new PharmacistRatingDTO(dermatologistProjection.getName(), dermatologistProjection.getSurname(), avg,dermatologistProjection.getEmail(),dermatologistProjection.getPharmacyName());
			dermatologistsWithRatings.add(dto);
		}
		return dermatologistsWithRatings;
	}
	public boolean deleteFrom(Long pharmacyId,Long userId) {
	Pharmacy pharm = 	this.pharmacyRepository.getById(pharmacyId);
	Set<Absence> absences = pharm.getAbsences();
		for (Absence abs :absences ) {
			if(abs.getUser().getId() == userId) {
				this.absenceRepository.deleteById(abs.getId());
			}
		}
		return true;
	}
	//OVO SE KORISTI EKSTRAKTOVANE METODE SU DOLE
	public Integer removePharmacist(Long pharmacyId,String email) {
		String str = email.substring(1, email.length() - 1);
		Long id = (long) 0;
       
		for (PharmacistDetailsProjection proj : this.pharmacistRepository.getAllByPharmacyId(pharmacyId, "PHARMACIST")) {

			if(proj.getEmail().trim().equals(str)) {
			 id = proj.getId();
			}
		}
		if(id!=0) {
		Pharmacy pharm = extracted(pharmacyId, id);
		boolean hasConsultation = hasConsultation(id, pharm);
		if(!hasConsultation) {
		deleteConsultation(id, pharm);
		deleteRatings(id);
//		deleteWorkDays(id);
		
		List<WorkDay> days = this.workdayRepository.findAll();
		for (WorkDay workDay : days) {
			System.out.println(workDay.getUser().getId());
			System.out.println(id);
			if(workDay.getUser().getId() == id) {
				this.workdayRepository.delete(workDay);
				System.out.println("UPALO OVDE");
			}
		}
		
		this.userRepository.deleteById(id);
		return 1;
		}
		return 0;
		}
		return 0;
	}

	public void deleteWorkDays(Long id) {
		List<WorkDay> days = this.workdayRepository.findAll();
		for (WorkDay workDay : days) {
			if(workDay.getUser().getId() == id) {
				this.workdayRepository.delete(workDay);
				System.out.println("UPALO OVDE");
			}
		}
	}

	public void deleteRatings(Long id) {
		List<PharmacistRating> ratings = this.pharmacistRatingRepository.findAll();
		for (PharmacistRating rating : ratings) {
			if(rating.getPharmacist().getId() == id) {
				this.pharmacistRatingRepository.delete(rating);
			}
		}
	}

	public void deleteConsultation(Long id, Pharmacy pharm) {
		Set<Consultation> cons = pharm.getConsultations();
//		Set<Consultation> todelete = new 
		for (Consultation consultation : cons) {
			if(consultation.getPharmacist().getId() == id) {

				this.consultationRepository.delete(consultation);
			}
		}
	}
	
	public boolean hasConsultation(Long id, Pharmacy pharm) {
		Set<Consultation> cons = pharm.getConsultations();
//		Set<Consultation> todelete = new 
		for (Consultation consultation : cons) {
			if(consultation.getPharmacist().getId() == id) {

				if(!consultation.isFinished()) {
					return true;
				}
			}
		}
		return false;
	}
	

	public Pharmacy extracted(Long pharmacyId, Long id) {
		Pharmacy pharm = this.pharmacyRepository.getById(pharmacyId);
		Set<Absence> absences = pharm.getAbsences();
			for (Absence abs :absences ) {
				if(abs.getUser().getId() == id) {
					//this.absenceRepository.deleteById(abs.getId());
					System.out.println(abs.getUser().getEmail());
					this.absenceRepository.deleteByManyProperties(abs.getUser().getId(), abs.getId());
				}
			}
		return pharm;
	}
}
