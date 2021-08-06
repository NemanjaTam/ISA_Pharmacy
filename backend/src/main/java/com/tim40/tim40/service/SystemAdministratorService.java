package com.tim40.tim40.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim40.tim40.model.SystemAdministrator;
import com.tim40.tim40.repository.SystemAdministratorRepository;
@Service
public class SystemAdministratorService  implements ISystemAdministratorService{

	@Autowired
	private SystemAdministratorRepository systemAdminRepository;
	
	@Override
	public SystemAdministrator findByiD(long id) {
		return systemAdminRepository.findById(id);
	}

	@Override
	public SystemAdministrator save(SystemAdministrator systemAdmin) {
		return systemAdminRepository.save(systemAdmin);
	}
	
	@Override
	public SystemAdministrator findByEmail(String email) {
		return systemAdminRepository.findByEmail(email);
	}

	@Override
	public List<SystemAdministrator> findAll() {
	        List<SystemAdministrator> result = systemAdminRepository.findAll();
	        return result;
	    }

}
