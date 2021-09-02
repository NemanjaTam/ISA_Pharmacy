package com.tim40.tim40.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tim40.tim40.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
    @Modifying
    @Query(value= "delete from users as a  where a.id = :id",nativeQuery = true)
    void deleteById(@Param("id") Long id);
}
