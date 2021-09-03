package com.tim40.tim40.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Tuple;
import java.util.stream.Collectors;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.tim40.tim40.dto.DermatologistDTO;
import com.tim40.tim40.dto.DermatologistDetailsDTO;
import com.tim40.tim40.dto.DermatologistRatingDTO;
import com.tim40.tim40.dto.dermDTO;
import com.tim40.tim40.model.Absence;
import com.tim40.tim40.model.Appointment;
import com.tim40.tim40.model.Consultation;
import com.tim40.tim40.model.Dermatologist;
import com.tim40.tim40.model.DermatologistRating;
import com.tim40.tim40.model.Patient;
import com.tim40.tim40.model.PharmacistRating;
import com.tim40.tim40.model.Pharmacy;
import com.tim40.tim40.model.WorkDay;
import com.tim40.tim40.projections.DermatologistDetailsProjection;
import com.tim40.tim40.projections.DermatologistPharmacyProjection;
import com.tim40.tim40.projections.DermatologistProjection;
import com.tim40.tim40.projections.PharmacistDetailsProjection;
import com.tim40.tim40.repository.AbsenceRepository;
import com.tim40.tim40.repository.AppointmentRepository;
import com.tim40.tim40.repository.DermatologistRatingRepository;
import com.tim40.tim40.repository.DermatologistRepository;
import com.tim40.tim40.repository.PharmacyRepository;
import com.tim40.tim40.repository.UserRepository;
import com.tim40.tim40.repository.WorkDayRepository;

@Service
public class DermatologistService implements IDermatologistService {

	private DermatologistRepository dermatologistRepository;
	private DermatologistRatingRepository dermatologistRatingRepository;
	private UserRepository userRepository;
	private WorkDayRepository workDayRepository;
	private AppointmentRepository appointmentRepository;
	private PharmacyRepository pharmacyRepository;
	private AbsenceRepository absenceRepository;


	@Autowired
	public DermatologistService(DermatologistRepository dermatologistRepository,DermatologistRatingRepository dermatologistRatingRepository,
			UserRepository userRepository,WorkDayRepository workDayRepository, AppointmentRepository appointmentRepository,
			PharmacyRepository pharmacyRepository, AbsenceRepository absenceRepository) {
		this.dermatologistRepository = dermatologistRepository;
		this.dermatologistRatingRepository = dermatologistRatingRepository;
		this.userRepository = userRepository;
		this.workDayRepository = workDayRepository;
		this.appointmentRepository = appointmentRepository;
		this.pharmacyRepository = pharmacyRepository;
		this.absenceRepository = absenceRepository;
	}

	@Override
	public ResponseEntity<Set<Patient>> getAllPatients(Long id) {
		Dermatologist dermatologist = dermatologistRepository.findById(id).get();
		return new ResponseEntity<Set<Patient>>(dermatologist.getAllPatients(), HttpStatus.OK);
	}

	//ne menjati: metoda koja vraca za id farmacije
	@Override
	public ResponseEntity<List<DermatologistProjection>> getAllDermatologists(Long ID) {
	   List<DermatologistProjection> dermatologist = dermatologistRepository.getAllByPharmacyId(ID);
	   return new ResponseEntity<List<DermatologistProjection>>(dermatologist, HttpStatus.OK);
		
	}
	//ne menjati,metoda koja vraca sve moguce dermatologe->projekcija dermatologa
	public ResponseEntity<List<DermatologistDetailsProjection>> getAllDermatologistsForAllPharmacies() {
		   List<DermatologistDetailsProjection> dermatologist = dermatologistRepository.getAllByPharmacyIdWithPharmacyList();
		   return new ResponseEntity<List<DermatologistDetailsProjection>>(dermatologist, HttpStatus.OK);
			
		}
	//ne menjati,metoda koja vraca dermatologe i sve apoteke u kojima rade
	public ResponseEntity<List<DermatologistRatingDTO>> GetDermatologistsAndPharmacyNames(){
		List<Dermatologist> dermatologists = dermatologistRepository.findAll();
		List<dermDTO> listDTO = new ArrayList<dermDTO>();
		for (Dermatologist dermatologist : dermatologists) {
			List<Integer> pharmacyId = this.dermatologistRepository.getPharmacies(dermatologist.getId());
			dermDTO derm = new dermDTO();
			derm.setId(dermatologist.getId());
			derm.setEmail(dermatologist.getEmail());
			derm.setName(dermatologist.getName());
			derm.setSurname(dermatologist.getSurname());
			for (Integer pharm : pharmacyId) {
				if(derm.getPharmacyNames() == null) {
					derm.setPharmacyNames(this.pharmacyRepository.getById(Long.valueOf(pharm)).getName());
				}else {
				derm.setPharmacyNames(this.pharmacyRepository.getById(Long.valueOf(pharm)).getName() + " " + derm.getPharmacyNames());
				}
			}
			listDTO.add(derm);
		}
		
		List<DermatologistRatingDTO> dermatologistsWithRatings = new ArrayList<DermatologistRatingDTO>();
		List<DermatologistRating> ratings = this.dermatologistRatingRepository.findAll();
		for (dermDTO dermatologistProjection : listDTO) {
			int rated = 0;
			int size = 0;
			for (DermatologistRating dermatologistRating : ratings) {
				if(dermatologistRating.getDermatologist().getId() == dermatologistProjection.getId()) {
					
					rated = rated + dermatologistRating.getRating();
				
					size = size + 1;				
				}
			}
	    System.out.println(rated);
		double avg = Double.valueOf(rated) / Double.valueOf(size);
		System.out.println(avg);
		System.out.println(size);
		DermatologistRatingDTO dto = new DermatologistRatingDTO(dermatologistProjection.getName(), dermatologistProjection.getSurname(), avg,dermatologistProjection.getEmail(),dermatologistProjection.getPharmacyNames());
			dermatologistsWithRatings.add(dto);
		}
		
		
		
		
		

	return new ResponseEntity<List<DermatologistRatingDTO>>(dermatologistsWithRatings, HttpStatus.OK);
	}
	public List<DermatologistRatingDTO> getRatingsForDermatologists(Long id){
		List<DermatologistRatingDTO> dermatologistsWithRatings = new ArrayList<DermatologistRatingDTO>();
		List<DermatologistRating> ratings = this.dermatologistRatingRepository.findAll();
		for (DermatologistProjection dermatologistProjection : this.dermatologistRepository.getAllByPharmacyId(id)) {
			int rated = 0;
			int size = 0;
			for (DermatologistRating dermatologistRating : ratings) {
				if(dermatologistRating.getDermatologist().getId() == dermatologistProjection.getId()) {
					
					rated = rated + dermatologistRating.getRating();
				
					size = size + 1;				
				}
			}
	    System.out.println(rated);
		double avg = Double.valueOf(rated) / Double.valueOf(size);
		System.out.println(avg);
		System.out.println(size);
		DermatologistRatingDTO dto = new DermatologistRatingDTO(dermatologistProjection.getName(), dermatologistProjection.getSurname(), avg,dermatologistProjection.getEmail(),dermatologistProjection.getPharmacyName());
			dermatologistsWithRatings.add(dto);
		}
		return dermatologistsWithRatings;
	}
	
	
//	public List<DermatologistRatingDTO> getRatingsForDermatologistsForAll(){
//		List<DermatologistRatingDTO> dermatologistsWithRatings = new ArrayList<DermatologistRatingDTO>();
//		List<DermatologistRating> ratings = this.dermatologistRatingRepository.findAll();
//		for (DermatologistDetailsProjection dermatologistProjection : this.dermatologistRepository.getDummy()) {
//			int rated = 0;
//			int size = 0;
//			for (DermatologistRating dermatologistRating : ratings) {
//				if(dermatologistRating.getDermatologist().getId() == dermatologistProjection.getId()) {
//					
//					rated = rated + dermatologistRating.getRating();
//				
//					size = size + 1;				
//				}
//			}
//	    System.out.println(rated);
//		double avg = Double.valueOf(rated) / Double.valueOf(size);
//		System.out.println(avg);
//		System.out.println(size);
//		DermatologistRatingDTO dto = new DermatologistRatingDTO(dermatologistProjection.getName(), dermatologistProjection.getSurname(), avg,dermatologistProjection.getEmail(),dermatologistProjection.getPharmacyName());
//			dermatologistsWithRatings.add(dto);
//		}
//		return dermatologistsWithRatings;
//	}
	
	public Integer removeDermatologist(Long pharmacyId,String email) {
	
		String str = email.substring(1, email.length() - 1);
		Long id = (long) 0;
		for(DermatologistProjection proj : this.dermatologistRepository.getAllByPharmacyId(pharmacyId)) {
			System.out.println(proj.getEmail());
			if(proj.getEmail().trim().equals(str)) {
				System.out.println("USLO OVDE 2");
			 id = proj.getId();
			}
		}
		if(id !=0) {
			System.out.println("USLO OVDE 3	");
		Pharmacy pharm = extracted(pharmacyId, id);
		boolean hasAppointments = hasAppointment(id, pharm);
		if(!hasAppointments) {
		deleteAppointment(id, pharm);

		deleteRatings(id);
		deleteWorkDays(id);
		this.dermatologistRepository.deleteById(id, pharmacyId);
		return 1;
		}
		return 0;
		}
		return 0;
	}

	public void deleteWorkDays(Long id) {
		List<WorkDay> days = this.workDayRepository.findAll();
		for (WorkDay workDay : days) {
			if(workDay.getUser().getId() == id) {
				this.workDayRepository.delete(workDay);
			}
		}
	}

	public void deleteRatings(Long id) {
		List<DermatologistRating> ratings = this.dermatologistRatingRepository.findAll();
		for (DermatologistRating rating : ratings) {
			if(rating.getDermatologist().getId() == id) {
				this.dermatologistRatingRepository.delete(rating);
			}
		}
	}

	public void deleteAppointment(Long id, Pharmacy pharm) {
		Set<Appointment> cons = pharm.getAppointments();

		for (Appointment consultation : cons) {
			if(consultation.getDermatologist().getId() == id) {
				this.appointmentRepository.delete(consultation);
			}
		}
	}
	
	public boolean hasAppointment(Long id, Pharmacy pharm) {
		Set<Appointment> cons = pharm.getAppointments();

		for (Appointment consultation : cons) {
			System.out.println(consultation.getId());
			if(consultation.getDermatologist().getId() == id) {
				System.out.println(consultation.isFinished() + "DA LI JE ZAVRSENO");
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
	
	
	public Object createAppointment(LocalDateTime start,LocalDateTime end,Integer price,Long dermatologistId) {
		return null;
	}
	
	public Long getIdByEmail(String email,Long pharmacyId) {
		String str = email.substring(1, email.length() - 1);
		Long id = (long) 0;
		for(DermatologistProjection proj : this.dermatologistRepository.getAllByPharmacyId(pharmacyId)) {
			System.out.println(proj.getEmail());
			if(proj.getEmail().trim().equals(str)) {
			 id = proj.getId();
			}
		}
		return id;
	}
	//ZA DERMATOLOGE DOBAVLJAM --> ODRADITI	
	public List<DermatologistDTO> getAllDermatologistNotInThisPharmacy(Long id){
		
		List<DermatologistDTO> newDermatologists = new ArrayList<DermatologistDTO>();
		List<Dermatologist> all = this.dermatologistRepository.findAll();
		List<DermatologistPharmacyProjection> relationships = this.dermatologistRepository.getDermatologistPharmacy();
		for (Dermatologist derm : all) {
			boolean exists = false;
			for (DermatologistPharmacyProjection proj : relationships) {
				
				System.out.println(proj.getDermatologistId());
				System.out.println(proj.getPharmacyId());
				if((proj.getPharmacyId() == id) && (proj.getDermatologistId() == derm.getId())) {
					System.out.println("USLO OVDE");
					exists = true;
					
				}
			}
			if(!exists) {
				
				DermatologistDTO dto = new DermatologistDTO(derm.getId(),derm.getName(),derm.getSurname(),derm.getEmail());
				newDermatologists.add(dto);
				System.out.println("uslo je u if");
			}
		}
		
		return newDermatologists;
	}
}


