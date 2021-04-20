package com.tim40.tim40.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim40.tim40.repository.WorkDayRepository;

@Service
public class WorkDayService implements IWorkDayService {
	
	private WorkDayRepository workDayRepository;

	@Autowired
	public WorkDayService(WorkDayRepository workDayRepository) {
		this.workDayRepository = workDayRepository;
	}
	
}
