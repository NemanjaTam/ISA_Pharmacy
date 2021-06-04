package com.tim40.tim40.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tim40.tim40.dto.LoginDTO;
import com.tim40.tim40.dto.UserDTO;
import com.tim40.tim40.model.User;

public interface IUserService {
	List<User> findAll();
	User findById(Long id);
	User update(User user) throws Exception;
    void delete(Long id);
	UserDTO register(UserDTO userDTO) throws Exception;
	
	ResponseEntity<UserDTO> login(LoginDTO loginDTO);
	ResponseEntity<UserDTO> updateUser(UserDTO userDTO);
}
