package com.tim40.tim40.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tim40.tim40.model.LoyaltyProgram;


public interface LoyaltyProgramRepository extends JpaRepository<LoyaltyProgram, Long> {
	
	@Query("select lp from LoyaltyProgram lp order by lp.bodovi desc")
	public Collection<LoyaltyProgram> getAllOrdered();
	
	@Query("select lp from LoyaltyProgram lp where lp.bodovi = ?1 or lp.naziv like ?2")
	LoyaltyProgram getTipWithPointsOrName(int bodovi, String naziv);

}

