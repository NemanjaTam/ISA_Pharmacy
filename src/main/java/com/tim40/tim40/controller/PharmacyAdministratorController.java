package com.tim40.tim40.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim40.tim40.service.PharmacyAdministratorService;

@RestController
@RequestMapping(value = "/pharmacyAdministrator")
public class PharmacyAdministratorController {
   @Autowired	
   private PharmacyAdministratorService pharmacyAdminService;
}
