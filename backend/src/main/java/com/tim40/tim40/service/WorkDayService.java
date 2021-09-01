package com.tim40.tim40.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tim40.tim40.model.Absence;
import com.tim40.tim40.model.Period;
import com.tim40.tim40.model.WorkDay;
import com.tim40.tim40.repository.AbsenceRepository;
import com.tim40.tim40.repository.WorkDayRepository;

@Service
public class WorkDayService implements IWorkDayService {
	
	private WorkDayRepository workDayRepository;
	private AbsenceRepository absenceRepository;

	@Autowired
	public WorkDayService(WorkDayRepository workDayRepository,AbsenceRepository absenceRepository) {
		this.workDayRepository = workDayRepository;
		this.absenceRepository = absenceRepository;
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
