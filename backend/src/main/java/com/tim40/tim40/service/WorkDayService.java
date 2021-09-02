package com.tim40.tim40.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tim40.tim40.model.WorkDay;
import com.tim40.tim40.repository.WorkDayRepository;

@Service
public class WorkDayService implements IWorkDayService {
	
	private WorkDayRepository workDayRepository;

	@Autowired
	public WorkDayService(WorkDayRepository workDayRepository) {
		this.workDayRepository = workDayRepository;
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
	
}
