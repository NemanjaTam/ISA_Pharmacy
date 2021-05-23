package com.tim40.tim40.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim40.tim40.dto.PharmacyDTO;
import com.tim40.tim40.dto.UserDTO;
import com.tim40.tim40.model.Address;
import com.tim40.tim40.model.Pharmacy;
import com.tim40.tim40.model.PharmacyAdministrator;
import com.tim40.tim40.model.User;
import com.tim40.tim40.repository.PharmacyAdministratorRepository;
import java.util.List;
@Service
public class PharmacyAdministratorService implements IPharmacyAdministratorService{

	private PharmacyAdministratorRepository pharmacyAdminRepository;
	
	@Autowired
	public PharmacyAdministratorService (PharmacyAdministratorRepository pharmacyAdminRepository) {
		this.pharmacyAdminRepository = pharmacyAdminRepository;
	}
	
	
	@Override
	public PharmacyAdministrator findByiD(long id) {
		return pharmacyAdminRepository.findById(id);
	}

	@Override
	public PharmacyAdministrator save(PharmacyAdministrator pharmacyAdmin) {
		return null;
	//to do
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
	
	public UserDTO register(UserDTO userDTO) throws Exception {
		Address address = new Address(userDTO.getAddress().getState(), userDTO.getAddress().getCity(),
				userDTO.getAddress().getStreet(), userDTO.getAddress().getNumber(), userDTO.getAddress().getPostalCode());
		
		User user = new User(userDTO.getName(), userDTO.getSurname(), userDTO.getEmail(), userDTO.getPassword(), address, userDTO.getUserType(),true);
		//user = this.pharmacyAdminRepository.save(user);
		return new UserDTO (user);
	

	}
}
