package com.tim40.tim40.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.tim40.tim40.dto.ComplaintDTO;
import com.tim40.tim40.dto.LoginDTO;
import com.tim40.tim40.dto.UserDTO;
import com.tim40.tim40.model.Address;
import com.tim40.tim40.model.Appointment;
import com.tim40.tim40.model.Complaint;
import com.tim40.tim40.model.Consultation;
import com.tim40.tim40.model.Dermatologist;
import com.tim40.tim40.model.Patient;
import com.tim40.tim40.model.Pharmacist;
import com.tim40.tim40.model.Pharmacy;
import com.tim40.tim40.model.SystemAdmin;
import com.tim40.tim40.model.User;
import com.tim40.tim40.repository.ComplaintRepository;
import com.tim40.tim40.repository.DermatologistRepository;
import com.tim40.tim40.repository.PatientRepository;
import com.tim40.tim40.repository.PharmacistRepository;
import com.tim40.tim40.repository.PharmacyRepository;
import com.tim40.tim40.repository.SystemAdminRepository;
import com.tim40.tim40.repository.UserRepository;

@Service
public class UserService implements IUserService {

	private UserRepository userRepository;
	private ComplaintRepository complaintRepository;
	private DermatologistRepository dermatologRepository;
	private PharmacistRepository pharmacistRepository;
	private PharmacyRepository pharmacyRepository;
	private PatientRepository patientRepository;
	private SystemAdminRepository systemAdminRepository;
	
	@Autowired
	public UserService(UserRepository userRepository, ComplaintRepository complaintRepository, DermatologistRepository dermatologRepository, PharmacistRepository pharmacistRepository,
			PharmacyRepository pharmacyRepository, PatientRepository patientRepository,SystemAdminRepository systemAdminRepository ) {
		this.userRepository = userRepository;
		this.complaintRepository = complaintRepository;
		this.dermatologRepository = dermatologRepository;
		this.pharmacistRepository = pharmacistRepository;
		this.pharmacyRepository = pharmacyRepository;
		this.patientRepository = patientRepository;
		this.systemAdminRepository = systemAdminRepository;
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

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email).orElse(null);
	}
	
	@Override
	public List<String> getComplainable(Long id) {
		List<Complaint> complaints = complaintRepository.findAll();
		for (Complaint complaint : complaints) {
			System.out.println("EVE GA " + complaint.getText());
		}
		List<Dermatologist> derme = dermatologRepository.findAll();
		List<String> appealable = new ArrayList<String>();
		for (Dermatologist dermatologist : derme) {
			Collection<Appointment> a = dermatologRepository.getExaminationsForPatientAndDermatologist(dermatologist.getId(),id);
			if(a.size() > 0) {
				appealable.add("Dermatologist: " + dermatologist.getName() + " " + dermatologist.getSurname());
				continue;
			}
		}
		List<Pharmacist> pharme = pharmacistRepository.findAll();
		for (Pharmacist pharmacist : pharme) {
			Collection<Consultation> cons = pharmacistRepository.getExaminationsForPatientAndPharmacist(pharmacist.getId(), id);
			if(cons.size() > 0) {
				appealable.add("Pharmacist: " + pharmacist.getName() + " " + pharmacist.getSurname());
				continue;
			}
		}
		
		List<Pharmacy> pharms = pharmacyRepository.findAll();
		for (Pharmacy pharmacy : pharms) {
			Collection<Appointment> a = dermatologRepository.getExaminationsForPatientAndPharmacy(pharmacy.getId(),id);
			Collection<Consultation> cons = pharmacistRepository.getExaminationsForPatientAndPharmacy(pharmacy.getId(), id);
			if(a.size() == 0 && cons.size() == 0) {
				continue;
			}
			appealable.add("Pharmacy: " + pharmacy.getName() + ", " + pharmacy.getAddress().getStreet() + " " + pharmacy.getAddress().getNumber() + ", " + pharmacy.getAddress().getCity());
		}
		return appealable;
		
		
	}

	@Override
	public void sendComplaint(ComplaintDTO comp) {
		Complaint c = new Complaint();
		Patient p = patientRepository.findById(comp.getPatId()).get();
		System.out.println("EVO OVDE ISPOD IDE TEKST");
		System.out.println(comp.getText());
		c.setText(comp.getText());
		c.setReply("");
		c.setSystemAdmin(null);
		Set<Complaint> lc = p.getComplaints();
		lc.add(c);
		p.setComplaints(lc);
		c.setPatient(p);
		complaintRepository.save(c);
		
		
	}

	@Override
	public void respondToComplaint(ComplaintDTO comp) {
		// TODO Auto-generated method stub
		System.out.println(comp);
		Complaint c = complaintRepository.getById(comp.getId());
		SystemAdmin sysAdm = systemAdminRepository.findById(comp.getAdmId()).get();
		Set<Complaint> cmps = sysAdm.getComplaints();
		cmps.add(c);
		sysAdm.setComplaints(cmps);
		c.setSystemAdmin(sysAdm);
		c.setReply(comp.getReply());
		complaintRepository.save(c);
		
	}

	@Override
	public List<ComplaintDTO> complaintsForUser(Long id) {
		// TODO Auto-generated method stub
		List<Complaint> allComps = (List<Complaint>) complaintRepository.getAllComplaintsForUser(id);
		List<ComplaintDTO> compsDto = new ArrayList<ComplaintDTO>();
		for (Complaint co : allComps) {
			ComplaintDTO cdto = new ComplaintDTO();
			if(co.getSystemAdmin() == null) {
				cdto = new ComplaintDTO(co.getId(), co.getText(), "", co.getPatient().getId(), null);
			} else {
				cdto = new ComplaintDTO(co.getId(), co.getText(), co.getReply(), co.getPatient().getId(), co.getSystemAdmin().getId());
			}
			//ComplaintDTO cdto = new ComplaintDTO(co.getId(), co.getText(), co.getReply(), co.getPatient().getId(), co.getSystemAdmin().getId());
			compsDto.add(cdto);
		}
		return compsDto;
	}

	@Override
	public List<ComplaintDTO> complaintsForAdmin(Long id) {
		// TODO Auto-generated method stub
		List<Complaint> allComps = (List<Complaint>) complaintRepository.getAllComplaintsForAdmin(id);
		List<ComplaintDTO> compsDto = new ArrayList<ComplaintDTO>();
		for (Complaint co : allComps) {
			ComplaintDTO cdto = new ComplaintDTO();
			if(co.getSystemAdmin() == null) {
				cdto = new ComplaintDTO(co.getId(), co.getText(), "", co.getPatient().getId(), null);
			} else {
				cdto = new ComplaintDTO(co.getId(), co.getText(), co.getReply(), co.getPatient().getId(), co.getSystemAdmin().getId());
			}
			//ComplaintDTO cdto = new ComplaintDTO(co.getId(), co.getText(), co.getReply(), co.getPatient().getId(), co.getSystemAdmin().getId());
			compsDto.add(cdto);
		}
		return compsDto;
	}

	@Override
	public ComplaintDTO getOneComplaint(Long id) {
		// TODO Auto-generated method stub
		Complaint c =  complaintRepository.findById(id).get();
		ComplaintDTO cdto = new ComplaintDTO();
		if(c.getSystemAdmin() == null) {
			cdto = new ComplaintDTO(c.getId(), c.getText(), "", c.getPatient().getId(), null);
		} else {
			cdto = new ComplaintDTO(c.getId(), c.getText(), c.getReply(), c.getPatient().getId(), c.getSystemAdmin().getId());
		}
		return cdto;
	}
}
