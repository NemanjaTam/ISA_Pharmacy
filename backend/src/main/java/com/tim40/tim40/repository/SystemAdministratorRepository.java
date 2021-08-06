package com.tim40.tim40.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tim40.tim40.model.SystemAdministrator;

public interface SystemAdministratorRepository  extends JpaRepository<SystemAdministrator, Long>{

	SystemAdministrator findById(long id);
	SystemAdministrator findByEmail(String email);
    List<SystemAdministrator> findAll();
    
}
