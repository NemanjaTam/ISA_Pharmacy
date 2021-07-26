package com.tim40.tim40.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tim40.tim40.dto.ConsultationDTO;
import com.tim40.tim40.model.Appointment;
import com.tim40.tim40.model.Consultation;
import com.tim40.tim40.model.Patient;
import com.tim40.tim40.model.Period;
import com.tim40.tim40.model.Pharmacist;
import com.tim40.tim40.model.Pharmacy;
import com.tim40.tim40.model.Therapy;
import com.tim40.tim40.model.WorkDay;
import com.tim40.tim40.repository.AppointmentRepository;
import com.tim40.tim40.repository.ConsultationRepository;
import com.tim40.tim40.repository.PatientRepository;
import com.tim40.tim40.repository.PharmacistRepository;
import com.tim40.tim40.repository.PharmacyRepository;
import com.tim40.tim40.repository.WorkDayRepository;

@Service
public class ConsultationService implements IConsultationService {
	
	private ConsultationRepository consultationRepository;
	private PharmacistRepository pharmacistRepository;
	private PatientRepository patientRepository;
	private PharmacyRepository pharmacyRepository;
	private WorkDayRepository workDayRepository;
	private AppointmentRepository appointmentRepository;

	@Autowired
	public ConsultationService(ConsultationRepository consultationRepository, PharmacistRepository pharmacistRepository, 
			PatientRepository patientRepository, PharmacyRepository pharmacyRepository, WorkDayRepository workDayRepository, AppointmentRepository appointmentRepository) {
		this.consultationRepository = consultationRepository;
		this.pharmacistRepository = pharmacistRepository;
		this.patientRepository = patientRepository;
		this.pharmacyRepository = pharmacyRepository;
		this.workDayRepository = workDayRepository;
		this.appointmentRepository = appointmentRepository;
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

	@Override
	public ResponseEntity<List<Consultation>> getAvailableConsultations(LocalDateTime start, LocalDateTime end,
			Long pharmacyId, Long patientId, Long pharmacistId) {
		List<Consultation> allConsultations = generateAllPossibleConsultations(start, end, pharmacyId, patientId, pharmacistId);
		List<Consultation> availableConsultations = new ArrayList<Consultation>(); 
		for(Consultation consultation : allConsultations) {
			if(!filterByConsultation(consultation, pharmacistId, patientId)) {
				if(filterByPeriod(consultation, start, end)) {
					if(filterByWorkDay(consultation, pharmacistId)) {
						if(!filterByAppointment(consultation, pharmacistId, patientId)) {
							availableConsultations.add(consultation);
						}
					}
				}
			}
		}
		return new ResponseEntity<List<Consultation>>(availableConsultations,HttpStatus.OK);
	}
	
	private boolean filterByConsultation(Consultation consultation, Long pharmacistId, Long patientId) {
		for(Consultation c : consultationRepository.findAll()) {
			if(!c.isFinished()) {
				if(c.getPeriod().getStartTime().isEqual(consultation.getPeriod().getStartTime())) {
					if(c.getPatient().getId() == patientId) {
						return true;
					}
					else if(c.getPharmacist().getId() == pharmacistId) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private boolean filterByPeriod(Consultation consultation, LocalDateTime start, LocalDateTime end) {
		if(consultation.getPeriod().getStartTime().isAfter(start) && consultation.getPeriod().getEndTime().isBefore(end.plusMinutes(1))) {
			return true;
		}
		else return false;
	}
	
	private boolean filterByWorkDay(Consultation consultation, Long id) {
		List<WorkDay> workDays = new ArrayList<WorkDay>(); 
		for(WorkDay wd : workDayRepository.findAll()) {
			if(wd.getUser().getId() == id) {
				workDays.add(wd);
			}
		}
		for(WorkDay wd : workDays) {
			if(consultation.getPeriod().getStartTime().isAfter(wd.getPeriod().getStartTime()) && consultation.getPeriod().getEndTime().isBefore(wd.getPeriod().getEndTime().plusMinutes(1))) {
				return true;
			}
		}
		return false;
	}
	
	private boolean filterByAppointment(Consultation consultation, Long pharmacistId,Long patientId) {
//		List<Appointment> appointments = new ArrayList<Appointment>();
		for(Appointment appointment : appointmentRepository.findAll()) {
			if(!appointment.isFinished()) {
				if(appointment.getPeriod().getStartTime().isEqual(consultation.getPeriod().getStartTime())) {
					if(appointment.getDermatologist().getId() == pharmacistId) {
						return true;
					}
					else if(appointment.getPatient().getId() == patientId) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private List<Consultation> generateAllPossibleConsultations(LocalDateTime start, LocalDateTime end, Long pharmacyId, Long patientId, Long pharmacistId) {
		List<Consultation> consultations = new ArrayList<Consultation>();
		Pharmacy pharcmay = pharmacyRepository.findById(pharmacyId).get();
		Patient patient = patientRepository.findById(patientId).get();
		Pharmacist pharmacist = pharmacistRepository.findById(pharmacistId).get();
		for(LocalDateTime begin = start; begin.isBefore(end); begin = begin.plusMinutes(30)) {
			Consultation consultation = new Consultation();
			Period period = new Period();
			period.setStartTime(begin);
			period.setEndTime(begin.plusMinutes(30));
			consultation.setPeriod(period);
			consultation.setPatient(patient);
			consultation.setPharmacist(pharmacist);
			consultation.setPharmacy(pharcmay);
			consultation.setReport("");
			consultation.setTherapies(new HashSet<Therapy>());
			consultation.setFinished(false);
			consultations.add(consultation);
		}
		return consultations;
	}

	@Override
	public ResponseEntity<Consultation> scheduleConsultation(ConsultationDTO consultationDTO) {
		Consultation consultation = new Consultation(consultationDTO);
		consultationRepository.save(consultation);
		return new ResponseEntity<Consultation>(consultation, HttpStatus.OK);
	}
	
}
