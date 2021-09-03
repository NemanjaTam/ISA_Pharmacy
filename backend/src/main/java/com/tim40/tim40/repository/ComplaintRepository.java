package com.tim40.tim40.repository;

import java.util.Collection;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import com.tim40.tim40.model.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
	
	@Query("select c from Complaint c where c.patient.id = ?1")
	Collection<Complaint> getAllComplaintsForUser(Long id);
	
	@Query("select c from Complaint c where c.systemAdmin.id = ?1 or c.reply is null or c.reply = ''")
	Collection<Complaint> getAllComplaintsForAdmin(Long id);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select c from Complaint c where c.id = ?1")
	Complaint getComplaintforReply(Long id);
}
