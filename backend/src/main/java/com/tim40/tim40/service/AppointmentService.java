package com.tim40.tim40.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.tim40.tim40.model.Appointment;
import com.tim40.tim40.model.Consultation;
import com.tim40.tim40.model.Dermatologist;
import com.tim40.tim40.model.Patient;
import com.tim40.tim40.model.Therapy;
import com.tim40.tim40.model.WorkDay;
import com.tim40.tim40.repository.AppointmentRepository;
import com.tim40.tim40.repository.ConsultationRepository;
import com.tim40.tim40.repository.DermatologistRepository;
import com.tim40.tim40.repository.PatientRepository;
import com.tim40.tim40.repository.WorkDayRepository;

@Service
public class AppointmentService implements IAppointmentService {
	
	private AppointmentRepository appointmentRepository;
	private ConsultationRepository consultationRepository;
	private DermatologistRepository dermatologistRepository;
	private WorkDayRepository workDayRepository;
	private PatientRepository patientRepository;

	@Autowired
	public AppointmentService(AppointmentRepository appointmentRepository, DermatologistRepository dermatologistRepository, 
			ConsultationRepository consultationRepository, WorkDayRepository workDayRepository, PatientRepository patientRepository) {
		
		this.appointmentRepository = appointmentRepository;
		this.dermatologistRepository = dermatologistRepository;
		this.consultationRepository = consultationRepository;
		this.workDayRepository = workDayRepository;
		this.patientRepository = patientRepository;
	}

	@Override
	public ResponseEntity<List<Appointment>> getAllScheduledForDermatologist(LocalDateTime date, Long id) {
		LocalDate local = date.toLocalDate();
		List<Appointment> appointments = appointmentRepository.findAll();
		List<Appointment> appointmentsForDate = new ArrayList<Appointment>();
		for(Appointment appointment : appointments) {
			LocalDate localDate = appointment.getPeriod().getStartTime().toLocalDate();
			if(localDate.compareTo(local) == 0) {
				appointmentsForDate.add(appointment);
			}
		}
 		List<Appointment> scheduledAppointments = new ArrayList<Appointment>();
		for(Appointment appointment : appointmentsForDate) {
			if(!appointment.isFinished() && appointment.isTaken() && appointment.getDermatologist().getId() == id) {
				scheduledAppointments.add(appointment);
			}
		}
		return new ResponseEntity<List<Appointment>>(scheduledAppointments,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Appointment> finishAppointment(Long id, String report, Set<Therapy> therapies) {
		Appointment appointment = appointmentRepository.findById(id).get();
		appointment.setReport(report);
		appointment.setTherapies(therapies);
		appointment.setFinished(true);
		appointmentRepository.save(appointment);
		if(!report.equals("")) addPatient(appointment.getPatient(), appointment.getDermatologist().getId());
		return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);
	}
	
	public void addPatient(Patient patient, Long id) {
		Dermatologist dermatologist = dermatologistRepository.findById(id).get();
		dermatologist.getAllPatients().add(patient);
		dermatologistRepository.save(dermatologist);
	}

	@Override
	public ResponseEntity<List<Appointment>> getAvailableAppointments(LocalDateTime start, LocalDateTime end,
			Long pharmacyId, Long patientId, Long dermatologistId) {
		List<Appointment> availableAppointments = new ArrayList<Appointment>();
		List<Appointment> allAvailableAppointments = new ArrayList<Appointment>();
		List<Appointment> takenAppointments = new ArrayList<Appointment>();
		for(Appointment a : appointmentRepository.findAll()) {
			if(!a.isTaken()) {
				if(a.getPharmacy().getId() == pharmacyId) {
					allAvailableAppointments.add(a);
				}
			}
			else if(!a.isFinished()){
				if(a.getDermatologist().getId() == dermatologistId) {
					takenAppointments.add(a);
				}
				else if(a.getPatient().getId() == patientId) {
					takenAppointments.add(a);
				}
			}
		}
		for(Appointment appointment : allAvailableAppointments) {
			if(!isAppointmentTaken(appointment, takenAppointments)) {
				if(filterByPeriod(appointment, start, end) ) {
					if(filterByWorkDay(appointment, dermatologistId)) {
						if(!filterByConsultation(appointment, pharmacyId, patientId)) {
							availableAppointments.add(appointment);
						}
					}
				}
			}
		}
		return new ResponseEntity<List<Appointment>>(availableAppointments, HttpStatus.OK);
	}
	
	private boolean isAppointmentTaken(Appointment appointment, List<Appointment> appointments) {
		for(Appointment a : appointments) {
			if(a.getPeriod().getStartTime().compareTo(appointment.getPeriod().getStartTime()) == 0) {
				return true;
			}
		}
		return false;
	}
	
	private boolean filterByPeriod(Appointment appointment, LocalDateTime start, LocalDateTime end) {
		if(appointment.getPeriod().getStartTime().isAfter(start) && appointment.getPeriod().getEndTime().isBefore(end)) {
			return true;
		}
		else return false;
	}
	
	private boolean filterByWorkDay(Appointment appointment, Long id) {
		List<WorkDay> workDays = new ArrayList<WorkDay>(); 
		for(WorkDay wd : workDayRepository.findAll()) {
			if(wd.getUser().getId() == id) {
				workDays.add(wd);
			}
		}
		for(WorkDay wd : workDays) {
			if(appointment.getPeriod().getStartTime().isAfter(wd.getPeriod().getStartTime()) && appointment.getPeriod().getEndTime().isBefore(wd.getPeriod().getEndTime())) {
				return true;
			}
		}
		return false;
	}
	
	private boolean filterByConsultation(Appointment appointment, Long pharmacistId, Long patientId) {
		List<Consultation> consultations = new ArrayList<Consultation>();
		for(Consultation consultation : consultationRepository.findAll()) {
			if(!consultation.isFinished()) {
				if(consultation.getPatient().getId() == patientId) {
					consultations.add(consultation);
				}
				else if(consultation.getPharmacist().getId() == pharmacistId) {
					consultations.add(consultation);
				}
			}
		}
		for(Consultation consultation : consultations) {
			if(consultation.getPeriod().getStartTime().isEqual(appointment.getPeriod().getStartTime())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public ResponseEntity<Appointment> scheduleAppointment(Long appointmentId, Long patientId,
			Long dermatologistId) {
		Appointment appointment = appointmentRepository.findById(appointmentId).get();
		appointment.setDermatologist(dermatologistRepository.findById(dermatologistId).get());
		appointment.setPatient(patientRepository.findById(patientId).get());
		appointment.setTaken(true);
		appointmentRepository.save(appointment);
		return new ResponseEntity<Appointment>(appointment,HttpStatus.OK);
	}

	@Override
	public Map<String,Integer> getDoneAppointmentsForYear(Long id) {
		List<Appointment>finishedAppointments = getAllAppointmentsByPharmacyId(id);
		LocalDateTime now = LocalDateTime.now();
		int value = 1;
		Map<String, Integer> map = new HashMap<>();
		LocalDateTime yearBefore = LocalDateTime.of(now.getYear() - 1, now.getMonthValue(), now.getDayOfMonth(), 0, 0);
		System.out.println(yearBefore);
	    for (Appointment appointment : finishedAppointments) {
	    	System.out.println(appointment.getPeriod().getEndTime() + "VREME");
			if(appointment.getPeriod().getEndTime().isAfter(yearBefore) && appointment.getPeriod().getEndTime().isBefore(now)) {
				if(map.containsKey(appointment.getPeriod().getEndTime().getMonth().toString())) {
					map.put(appointment.getPeriod().getEndTime().getMonth().toString(),map.get(appointment.getPeriod().getEndTime().getMonth().toString()) + 1);
					
				}else {
					map.put(appointment.getPeriod().getEndTime().getMonth().toString(),value);
				}
		
				
			}
		}
	
		return map;
	}

	@Override
	public Map<String,Integer> getDoneAppointmentsForQuartal(Long id) {
		List<Appointment>finishedAppointments = getAllAppointmentsByPharmacyId(id);
		LocalDateTime now = LocalDateTime.now();
		int value = 1;
		Map<String, Integer> map = new HashMap<>();
		LocalDateTime yearBefore = LocalDateTime.of(now.getYear(), now.getMonthValue() - 4, now.getDayOfMonth(), 0, 0);
		System.out.println(yearBefore);
	    for (Appointment appointment : finishedAppointments) {
	    	System.out.println(appointment.getPeriod().getEndTime() + "VREME");
			if(appointment.getPeriod().getEndTime().isAfter(yearBefore) && appointment.getPeriod().getEndTime().isBefore(now)) {
				if(map.containsKey(appointment.getPeriod().getEndTime().getMonth().toString())) {
					map.put(appointment.getPeriod().getEndTime().getMonth().toString(),map.get(appointment.getPeriod().getEndTime().getMonth().toString()) + 1);
					
				}else {
					map.put(appointment.getPeriod().getEndTime().getMonth().toString(),value);
				}
		
				
			}
		}
		return map;
	}

	@Override
	public Map<String,Integer> getDoneAppointmentsForMonth(Long id) {
		List<Appointment>finishedAppointments = getAllAppointmentsByPharmacyId(id);
		LocalDateTime now = LocalDateTime.now();
		int value = 1;
		Map<String, Integer> map = new HashMap<>();
		LocalDateTime yearBefore = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), 0, 0);
		System.out.println(yearBefore);
	    for (Appointment appointment : finishedAppointments) {
	    	System.out.println(appointment.getPeriod().getEndTime() + "VREME");
			if(appointment.getPeriod().getEndTime().getMonth().equals(yearBefore.getMonth()) && appointment.getPeriod().getEndTime().getYear() == yearBefore.getYear()) {
				if(map.containsKey(appointment.getPeriod().getEndTime().getMonth().toString())) {
					map.put(appointment.getPeriod().getEndTime().getMonth().toString(),map.get(appointment.getPeriod().getEndTime().getMonth().toString()) + 1);
					
				}else {
					map.put(appointment.getPeriod().getEndTime().getMonth().toString(),value);
				}
		
				
			}
		}
		return map;
	}
//FINISHED APPOINTMENTS
	@Override
	public List<Appointment> getAllAppointmentsByPharmacyId(Long id) {
		List<Appointment> appointments = this.appointmentRepository.findAll();
		List<Appointment> finishedAppointments = new ArrayList<Appointment>();
		for (Appointment appointment : appointments) {
			if(appointment.isFinished() && appointment.getPharmacy().getId().equals(id)) {
				finishedAppointments.add(appointment);
			}
		}
		return finishedAppointments;
	}
}
