package com.tim40.tim40.controller;

import java.security.Principal;



import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tim40.tim40.dto.PharmacyAdminDTO;
import com.tim40.tim40.model.Address;
import com.tim40.tim40.model.PharmacyAdministrator;
import com.tim40.tim40.service.DermatologistService;
import com.tim40.tim40.service.PharmacyAdministratorService;
import com.tim40.tim40.service.UserService;

@RestController
@RequestMapping(value = "api/pharmacyAdministrator")
public class PharmacyAdministratorController {
   	
   private PharmacyAdministratorService pharmacyAdminService;
   @Autowired
   private UserService userService;
	@Autowired
	public PharmacyAdministratorController(PharmacyAdministratorService pharmacyAdminService) {
		this.pharmacyAdminService = pharmacyAdminService;
	}
	//EDIT PROFILE
	@RequestMapping(value="",method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> editPharmacyAdminData(@RequestBody PharmacyAdminDTO pharmacyAdminDTO, Principal p) {
	        PharmacyAdministrator pharmacyAdmin = (PharmacyAdministrator) userService.findByEmail(p.getName());

//	        if (pharmacyAdmin == null) {
//	            throw new ResourceConflictException("Pharmacy administrator not found.");
//	        }

	        pharmacyAdmin.setName(pharmacyAdminDTO.getName());
	        pharmacyAdmin.setSurname(pharmacyAdminDTO.getSurname());
	        pharmacyAdmin.setPhone(pharmacyAdminDTO.getPhone());
	        if((pharmacyAdmin.getAddress().getCity() != pharmacyAdminDTO.getCity()) || (pharmacyAdmin.getAddress().getState() != pharmacyAdminDTO.getState()) ||
	            (pharmacyAdmin.getAddress().getPostalCode() != pharmacyAdminDTO.getPostalCode() || (pharmacyAdmin.getAddress().getStreet() != pharmacyAdminDTO.getStreet()) ||
	            (pharmacyAdmin.getAddress().getNumber() != pharmacyAdminDTO.getStreet()))){

	            pharmacyAdmin.setAddress(new Address());
	            pharmacyAdmin.getAddress().setNumber(pharmacyAdminDTO.getStreet());
	            pharmacyAdmin.getAddress().setCity(pharmacyAdminDTO.getCity());
	            pharmacyAdmin.getAddress().setState(pharmacyAdminDTO.getState());
	            pharmacyAdmin.getAddress().setPostalCode(pharmacyAdminDTO.getPostalCode());
	            pharmacyAdmin.getAddress().setStreet(pharmacyAdminDTO.getStreet());
	        }


	        pharmacyAdminService.save(pharmacyAdmin);

	        return ResponseEntity.ok().build();
	    }

   
}
