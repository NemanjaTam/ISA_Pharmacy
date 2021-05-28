package com.tim40.tim40.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim40.tim40.dto.ChangePasswordDTO;
import com.tim40.tim40.dto.UserDTO;
import com.tim40.tim40.model.Address;
import com.tim40.tim40.model.User;
import com.tim40.tim40.repository.UserRepository;

@Service
public class UserService implements IUserService {

	private UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id).orElseGet(null);
	}

	@Override
	public UserDTO register(UserDTO userDTO) throws Exception {
		Address address = new Address(userDTO.getAddress().getState(), userDTO.getAddress().getCity(),
				userDTO.getAddress().getStreet(), userDTO.getAddress().getNumber(), userDTO.getAddress().getPostalCode());
		
		User user = new User(userDTO.getName(), userDTO.getSurname(), userDTO.getEmail(), userDTO.getPassword(), address, userDTO.getUserType(),true, userDTO.getPhone());
		user = this.userRepository.save(user);
		return new UserDTO (user);
	}

	@Override
	public User update(User user) throws Exception {
		return userRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	public boolean changePassword(ChangePasswordDTO cpDTO) {
		User user = userRepository.findById(cpDTO.getId()).orElseGet(null);
		user.setPassword(cpDTO.getPassword());
		user.setFirstTimeLogging(false);
		userRepository.save(user);
		return true;
	}
	
	

}
