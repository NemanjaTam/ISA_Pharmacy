package com.tim40.tim40.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tim40.tim40.service.SystemAdministratorService;
import com.tim40.tim40.service.UserService;

@RestController
@RequestMapping(value = "api/systemAdministrator")
public class SystemAdminController {

	private SystemAdministratorService systemAdministratorService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	public SystemAdminController(SystemAdministratorService systemAdministratorService) {
		this.systemAdministratorService= systemAdministratorService;
		}
	}
