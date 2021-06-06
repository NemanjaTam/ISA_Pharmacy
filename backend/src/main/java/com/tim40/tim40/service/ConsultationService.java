package com.tim40.tim40.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tim40.tim40.model.Consultation;
import com.tim40.tim40.model.Patient;
import com.tim40.tim40.model.Pharmacist;
import com.tim40.tim40.model.Therapy;
import com.tim40.tim40.repository.ConsultationRepository;
import com.tim40.tim40.repository.PharmacistRepository;

@Service
public class ConsultationService implements IConsultationService {
	
	private ConsultationRepository consultationRepository;
	private PharmacistRepository pharmacistRepository;

	@Autowired
	public ConsultationService(ConsultationRepository consultationRepository, PharmacistRepository pharmacistRepository) {
		this.consultationRepository = consultationRepository;
		this.pharmacistRepository = pharmacistRepository;
	}

	@Override
	public ResponseEntity<List<Consultation>> getAllScheduledForPharmacist(LocalDateTime date, Long id) {
		LocalDate local = date.toLocalDate();
		List<Consultation> consultations = consultationRepository.findAll();
		List<Consultation> consultationsForDate = new ArrayList<Consultation>();
		for(Consultation consultation : consultations) {
			LocalDate localDate = consultation.getPeriod().getStartTime().toLocalDate();
			if(localDate.compareTo(local) == 0) {
				consultationsForDate.add(consultation);
			}
		}
 		List<Consultation> scheduledConsultations = new ArrayList<Consultation>();
		for(Consultation consultation : consultationsForDate) {
			if(!consultation.isFinished() && consultation.getPharmacist().getId() == id) {
				scheduledConsultations.add(consultation);
			}
		}
		return new ResponseEntity<List<Consultation>>(scheduledConsultations,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Consultation> finishConsultation(Long id, String report, Set<Therapy> therapies) {
		Consultation consultation = consultationRepository.findById(id).get();
		consultation.setReport(report);
		consultation.setTherapies(therapies);
		consultation.setFinished(true);
		consultationRepository.save(consultation);
		if(!report.equals("")) addPatient(consultation.getPatient(), consultation.getPharmacist().getId());
		return new ResponseEntity<Consultation>(consultation, HttpStatus.OK);
	}
	
	public void addPatient(Patient patient, Long id) {
		Pharmacist pharmacist = pharmacistRepository.findById(id).get();
		pharmacist.getAllPatients().add(patient);
		pharmacistRepository.save(pharmacist);
	}
	
}
