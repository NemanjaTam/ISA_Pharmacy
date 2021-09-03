package com.tim40.tim40.repository;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import com.tim40.tim40.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Lock(LockModeType.PESSIMISTIC_READ)
	Optional<User> findByEmail(String email);
	
	
}
