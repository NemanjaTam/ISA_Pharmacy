package com.tim40.tim40.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim40.tim40.dto.UserDTO;
import com.tim40.tim40.model.User;
import com.tim40.tim40.service.UserService;

@RestController
@RequestMapping(value = "api/user")
public class UserController {
	
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@CrossOrigin(origins = "http://localhost:8081")
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDTO>> getAllUsers()
	{
		List<User> userList = userService.findAll();
		List<UserDTO> userListDTO = new ArrayList<>(); 
		for(User u : userList) {
			userListDTO.add(new UserDTO(u));
		}
		return new ResponseEntity<List<UserDTO>>(userListDTO, HttpStatus.OK);
	}

	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) throws Exception
	{
		UserDTO registeredUser = userService.register(userDTO);
		
		return new ResponseEntity<UserDTO>(registeredUser, HttpStatus.OK);
	}
}
