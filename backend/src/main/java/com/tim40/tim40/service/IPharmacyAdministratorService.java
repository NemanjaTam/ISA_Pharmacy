package com.tim40.tim40.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tim40.tim40.model.PharmacyAdministrator;
import com.tim40.tim40.projections.PharmacyProjection;

public interface IPharmacyAdministratorService {

	  public PharmacyAdministrator findByiD(long id);
	  public PharmacyAdministrator save(PharmacyAdministrator pharmacyAdmin);
	  public PharmacyAdministrator findByEmail(String email);
	  public List<PharmacyAdministrator> findAll();
	  Long getPharmacy(Long id);
	  public PharmacyProjection getPharmacyById(Long id,String type);
}
