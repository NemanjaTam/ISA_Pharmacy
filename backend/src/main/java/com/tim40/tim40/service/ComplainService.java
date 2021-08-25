package com.tim40.tim40.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tim40.tim40.dto.ComplainDTO;
import com.tim40.tim40.model.Complain;
import com.tim40.tim40.repository.ComplainRepository;

@Service
public class ComplainService implements IComplainService {

	private ComplainRepository complainRepository;
	
	@Autowired
	public ComplainService(ComplainRepository complainRepository) {
		this.complainRepository = complainRepository;
	}

	
	@Override
	public ResponseEntity<List<ComplainDTO>> getNonAnsweredComplains() {
		List<Complain> complains = complainRepository.findAll();
		List<ComplainDTO> complainDTOs = new ArrayList<>();
		
		for(Complain c : complains) {
			if(c.getResponse().length() == 0) {
				complainDTOs.add(new ComplainDTO(c));
			}
			
		}
		return new ResponseEntity<List<ComplainDTO>> (complainDTOs, HttpStatus.OK);
	}

	
}
