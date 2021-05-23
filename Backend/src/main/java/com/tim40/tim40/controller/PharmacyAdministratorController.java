package com.tim40.tim40.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim40.tim40.dto.UserDTO;
import com.tim40.tim40.service.PharmacyAdministratorService;

@RestController
@RequestMapping(value = "/pharmacyAdministrator")
public class PharmacyAdministratorController {
  
   private PharmacyAdministratorService pharmacyAdminService;
   
   @Autowired	
   public PharmacyAdministratorController(PharmacyAdministratorService pharmacyAdminService)
   {
	   this.pharmacyAdminService = pharmacyAdminService;
   }
   
   /*@PostMapping(value = "/adminRegister", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<UserDTO> adminRegister(@RequestBody UserDTO userDTO)   
   {
	   UserDTO adminRegisteredUser = pharmacyAdminService.adminRegister(userDTO); 
	   
	   return new ResponseEntity<UserDTO>(adminRegisteredUser, HttpStatus.OK);
   }*/
   
}
