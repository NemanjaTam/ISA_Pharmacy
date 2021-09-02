package com.tim40.tim40.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.aop.aspectj.AspectJMethodBeforeAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tim40.tim40.dto.NewWorkDaysDTO;
import com.tim40.tim40.dto.PeriodDTO;
import com.tim40.tim40.dto.ShiftsDTO;
import com.tim40.tim40.model.Absence;
import com.tim40.tim40.model.Dermatologist;
import com.tim40.tim40.model.Patient;
import com.tim40.tim40.model.Period;
import com.tim40.tim40.model.Pharmacist;
import com.tim40.tim40.model.Pharmacy;
import com.tim40.tim40.model.Therapy;
import com.tim40.tim40.model.User;
import com.tim40.tim40.model.WorkDay;
import com.tim40.tim40.model.enums.UserType;
import com.tim40.tim40.repository.AbsenceRepository;
import com.tim40.tim40.repository.DermatologistRepository;
import com.tim40.tim40.repository.PharmacyRepository;
import com.tim40.tim40.repository.UserRepository;
import com.tim40.tim40.repository.WorkDayRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
public class WorkDayService implements IWorkDayService {
	
	private WorkDayRepository workDayRepository;
	private AbsenceRepository absenceRepository;
	private UserRepository userRepository;
	private PharmacyRepository pharmacyRepository;
	private DermatologistRepository dermatologistRepository;

	@Autowired
	public WorkDayService(WorkDayRepository workDayRepository,AbsenceRepository absenceRepository,UserRepository userRepository,PharmacyRepository pharmacyRepository
			,DermatologistRepository dermatologistRepository) {
		this.workDayRepository = workDayRepository;
		this.absenceRepository = absenceRepository;
		this.userRepository = userRepository;
		this.pharmacyRepository = pharmacyRepository;
		this.dermatologistRepository = dermatologistRepository;
	}

	
	public Integer createWorkDays(NewWorkDaysDTO dto,Long id,Long pharmacyId) {
		if(dto.getUserType().equals(UserType.PHARMACIST)) {
		
		User user = this.userRepository.getById(id);
		Pharmacy pharm = this.pharmacyRepository.getById(pharmacyId);
		List<WorkDay> workDays = new ArrayList<WorkDay>();
		
        if(dto.getShifts().size()>0) {
        	System.out.println("sve je u redu");
        }
	
		for (ShiftsDTO shift : dto.getShifts()) {
			WorkDay newWorkDay = new WorkDay();
			

			
			LocalDateTime start = LocalDateTime.of(shift.getStartTime().getYear(),shift.getStartTime().getMonthValue(),shift.getStartTime().getDayOfMonth(),shift.getStartShift(),0);
			LocalDateTime end = LocalDateTime.of(shift.getStartTime().getYear(),shift.getStartTime().getMonthValue(),shift.getStartTime().getDayOfMonth(),shift.getEndShift(),0);
			Period newPeriod = new Period(start,end);
			newWorkDay.setPeriod(newPeriod);
			newWorkDay.setPharmacy(pharm);
			newWorkDay.setUser(user);
			workDays.add(newWorkDay);
		}
		this.workDayRepository.saveAll(workDays);
		return workDays.size();
		}else if (dto.getUserType().equals(UserType.DERMATOLOGIST)){
			Dermatologist user = this.dermatologistRepository.getById(id);
			Pharmacy pharm = this.pharmacyRepository.getById(pharmacyId);
			this.dermatologistRepository.addNewDermatologistToPharmacy(id, pharmacyId);

			List<WorkDay> workDaysDerm = new ArrayList<WorkDay>();
			List<WorkDay> existingWorkDays = this.workDayRepository.findAll();
			for (ShiftsDTO shift : dto.getShifts()) {
				WorkDay newWorkDay = new WorkDay();
				
				LocalDateTime start = LocalDateTime.of(shift.getStartTime().getYear(),shift.getStartTime().getMonthValue(),shift.getStartTime().getDayOfMonth(),shift.getStartShift(),0);
				LocalDateTime end = LocalDateTime.of(shift.getStartTime().getYear(),shift.getStartTime().getMonthValue(),shift.getStartTime().getDayOfMonth(),shift.getEndShift(),0);
				Period newPeriod = new Period(start,end);
				
				LocalTime startTime = LocalTime.of(start.getHour(), start.getMinute(), start.getSecond());
				LocalTime endTime =  LocalTime.of(end.getHour(), end.getMinute(), end.getSecond());
				
				newWorkDay.setPeriod(newPeriod);
				newWorkDay.setPharmacy(pharm);
				newWorkDay.setUser(user);
				for (WorkDay workDay : existingWorkDays) {
					boolean exists = false;
					LocalTime startExisting = LocalTime.of(workDay.getPeriod().getStartTime().getHour(), workDay.getPeriod().getStartTime().getMinute(), workDay.getPeriod().getStartTime().getSecond());
					LocalTime endExsisting = LocalTime.of(workDay.getPeriod().getEndTime().getHour(), workDay.getPeriod().getEndTime().getMinute(),workDay.getPeriod().getEndTime().getSecond());
					if(workDay.getUser().getId() == user.getId()) {
						if((workDay.getPeriod().getStartTime().getYear() == shift.getStartTime().getYear()) &&(workDay.getPeriod().getStartTime().getMonthValue() == shift.getStartTime().getMonthValue())
							&& (workDay.getPeriod().getStartTime().getDayOfMonth() == shift.getStartTime().getDayOfMonth())) {
							if(!(endTime.isBefore(startExisting) || startTime.isAfter(endExsisting))) {
								
								exists = true;
								System.out.println("POSTOJI U BAZI");
							}
							
						}
					}
					
					if(!exists) {
						workDaysDerm.add(newWorkDay);}
					}
			}
			this.workDayRepository.saveAll(workDaysDerm);
			
		return workDaysDerm.size();
		
			
			
		}
		return null;
	}
	
	@Override
	public ResponseEntity<String> checkIsUserWorking(LocalDateTime date, Long userId) {
		LocalDate local = date.toLocalDate();
		List<WorkDay> workdays = workDayRepository.findAll();
		List<WorkDay> workDaysForUser = new ArrayList<WorkDay>();
		for(WorkDay wd : workdays) {
			if(wd.getUser().getId() == userId) {
				workDaysForUser.add(wd);
			}
		}
		for(WorkDay workDay : workdays) {
			LocalDate localDate = workDay.getPeriod().getStartTime().toLocalDate();
			if(localDate.compareTo(local) == 0) {
				return new ResponseEntity<String>("working",HttpStatus.OK);
			}
		}
		return new ResponseEntity<String>("not_working",HttpStatus.OK);
	}
	
	public List<Period> getAllWorkingDays(Long dermId,Long pharmacyId){
		List<WorkDay> workDays = new ArrayList<WorkDay>();
		List<WorkDay> notOnAbsence = new ArrayList<WorkDay>();
		for (WorkDay wd : this.workDayRepository.findAll()) {
			if(wd.getUser().getId() == dermId && wd.getPharmacy().getId() == pharmacyId) {
				workDays.add(wd);
				notOnAbsence.add(wd);
			}
		}
		
		List<Absence> absences = new ArrayList<Absence>();
		for (Absence absence : this.absenceRepository.findAll()) {
			if(absence.getPharmacy().getId() == pharmacyId) {
				absences.add(absence);
			}
		}
		
		for (WorkDay workday : workDays) {
			for (Absence absence : absences) {
				if(workday.getUser().getId() == absence.getUser().getId()) {
					LocalDateTime localDateTime1 = absence.getStartTime().atStartOfDay();
					LocalDateTime localDateTime2 = absence.getEndTime().atStartOfDay();
					if((workday.getPeriod().getStartTime().isAfter(localDateTime1)) || (workday.getPeriod().getStartTime().isEqual(localDateTime1))
							|| (workday.getPeriod().getStartTime().isEqual(localDateTime2))) {
						notOnAbsence.remove(workday);
					}
				}
			}
		}
		List<Period> periods = new ArrayList<Period>();
		for (WorkDay wrkdy : notOnAbsence) {
			periods.add(wrkdy.getPeriod());
		}
		return periods;
	}
	
}
