package com.tim40.tim40.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim40.tim40.service.WorkDayService;

@RestController
@RequestMapping(value = "api/workday")
public class WorkDayController {
	
	private WorkDayService workDayService;

	@Autowired
	public WorkDayController(WorkDayService workDayService) {
		this.workDayService = workDayService;
	}

}
