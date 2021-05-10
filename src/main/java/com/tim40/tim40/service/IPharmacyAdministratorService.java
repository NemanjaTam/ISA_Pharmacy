package com.tim40.tim40.service;

import java.util.List;

import com.tim40.tim40.model.PharmacyAdministrator;

public interface IPharmacyAdministratorService {

	  public PharmacyAdministrator findByiD(long id);
	  public PharmacyAdministrator save(PharmacyAdministrator pharmacyAdmin);
	  public PharmacyAdministrator findByEmail(String email);
	  public List<PharmacyAdministrator> findAll();
}
