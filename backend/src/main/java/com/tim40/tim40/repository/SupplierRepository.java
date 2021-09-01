  
package com.tim40.tim40.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tim40.tim40.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long>{
	Optional<Supplier> findByEmail(String email);
	
}