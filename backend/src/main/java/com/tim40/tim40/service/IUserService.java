package com.tim40.tim40.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tim40.tim40.dto.ComplaintDTO;
import com.tim40.tim40.dto.LoginDTO;
import com.tim40.tim40.dto.UserDTO;
import com.tim40.tim40.model.User;

public interface IUserService {
	List<User> findAll();
	User findById(Long id);
	User findByEmail(String email);
	User update(User user) throws Exception;
    void delete(Long id);
	UserDTO register(UserDTO userDTO) throws Exception;
	
	ResponseEntity<UserDTO> login(LoginDTO loginDTO);
	ResponseEntity<UserDTO> updateUser(UserDTO userDTO);
	ResponseEntity<String> checkIsEmailTaken(String email);
	
	List<String> getComplainable(Long id);
	void sendComplaint(ComplaintDTO comp);
	void respondToComplaint(ComplaintDTO comp);
	List<ComplaintDTO> complaintsForUser(Long id);
	List<ComplaintDTO> complaintsForAdmin(Long id);
	ComplaintDTO getOneComplaint(Long id);
}
