package com.tim40.tim40.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tim40.tim40.dto.AddNewWorkerDTO;
import com.tim40.tim40.dto.LoginDTO;
import com.tim40.tim40.dto.UserDTO;
import com.tim40.tim40.model.Address;
import com.tim40.tim40.model.Pharmacist;
import com.tim40.tim40.model.Pharmacy;
import com.tim40.tim40.model.User;
import com.tim40.tim40.model.enums.UserType;
import com.tim40.tim40.repository.PharmacyRepository;
import com.tim40.tim40.repository.UserRepository;

@Service
public class UserService implements IUserService {

	private UserRepository userRepository;
	private PharmacyRepository pharmacyRepository;
	
	@Autowired
	public UserService(UserRepository userRepository,PharmacyRepository pharmacyRepository) {
		this.userRepository = userRepository;
		this.pharmacyRepository = pharmacyRepository;
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
		
		User user = new User(userDTO.getName(), userDTO.getSurname(), userDTO.getEmail(), userDTO.getPassword(), address, userDTO.getUserType(),true);
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

	@Override
	public ResponseEntity<UserDTO> login(LoginDTO loginDTO) {
		List<User> users = userRepository.findAll();
		for(User user : users) {
			if(user.getEmail().equals(loginDTO.getEmail())) {
				if(user.getPassword().equals(loginDTO.getPassword())) {
					return new ResponseEntity<UserDTO>(new UserDTO(user),HttpStatus.OK);
				}
				UserDTO userDTO = new UserDTO();
				userDTO.setId((long) -1);
				return new ResponseEntity<UserDTO>(userDTO,HttpStatus.OK);
			}
		}
		UserDTO userDTO = new UserDTO();
		userDTO.setId((long) -2);
		return new ResponseEntity<UserDTO>(userDTO,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserDTO> updateUser(UserDTO userDTO) {
		User user = userRepository.getById(userDTO.getId());
		user.update(userDTO);
		userRepository.save(user);
		return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> checkIsEmailTaken(String email) {
		User user = userRepository.findByEmail(email).orElse(null);
		if(user == null) {
			return new ResponseEntity<String>("not_taken", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("taken", HttpStatus.OK);
		}
	}
//BOJANA,normalna registracija nije radila?!
	public Long registerByPharmacyAdmin(AddNewWorkerDTO userDTO,Long pharmacyId) throws Exception {
		List<User> users = this.userRepository.findAll();
		for (User user : users) {
			if(user.getEmail().equals(userDTO.getEmail())) {
				return (long) 0;
			}
		}
		Address address = new Address(userDTO.getAddress().getState(), userDTO.getAddress().getCity(),
				userDTO.getAddress().getStreet(), userDTO.getAddress().getNumber(), userDTO.getAddress().getPostalCode());
		
		Pharmacist user = new Pharmacist();
		Pharmacy pharm = this.pharmacyRepository.getById(pharmacyId);
		user.setAddress(address);
		user.setEmail(userDTO.getEmail());
		user.setName(userDTO.getName());
		user.setSurname(userDTO.getSurname());
		user.setFirstTimeLogging(true);
		user.setPassword(userDTO.getPassword());
		user.setPhone(userDTO.getPhone());
		user.setPharmacy(pharm);
		user.setUserType(UserType.PHARMACIST);
		user = this.userRepository.save(user);
		return  user.getId();
	}
	
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email).orElse(null);
	}
}
