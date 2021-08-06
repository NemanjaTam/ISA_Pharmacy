package com.tim40.tim40.service;

import java.util.List;
import com.tim40.tim40.model.SystemAdministrator;

public interface ISystemAdministratorService {

	 public SystemAdministrator findByiD(long id);
	  public SystemAdministrator save(SystemAdministrator systemAdmin);
	  public SystemAdministrator findByEmail(String email);
	  public List<SystemAdministrator> findAll();
}
