package com.tim40.tim40.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tim40.tim40.model.PharmacyAdministrator;
import com.tim40.tim40.repository.PharmacyAdministratorRepository;
import java.util.List;
@Service
public class PharmacyAdministratorService implements IPharmacyAdministratorService{

	@Autowired
	private PharmacyAdministratorRepository pharmacyAdminRepository;
	
	@Override
	public PharmacyAdministrator findByiD(long id) {
		return pharmacyAdminRepository.findById(id);
	}

	@Override
	public PharmacyAdministrator save(PharmacyAdministrator pharmacyAdmin) {
		return pharmacyAdminRepository.save(pharmacyAdmin);
	}
	
	@Override
	public PharmacyAdministrator findByEmail(String email) {
		return pharmacyAdminRepository.findByEmail(email);
	}

	@Override
	public List<PharmacyAdministrator> findAll() {
	        List<PharmacyAdministrator> result = pharmacyAdminRepository.findAll();
	        return result;
	    }

}
