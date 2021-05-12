package com.tim40.tim40.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim40.tim40.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	
}
