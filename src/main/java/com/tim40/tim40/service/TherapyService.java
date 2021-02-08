package com.tim40.tim40.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim40.tim40.repository.TherapyRepository;

@Service
public class TherapyService implements ITherapyService {

	private TherapyRepository therapyRepository;

	@Autowired
	public TherapyService(TherapyRepository therapyRepository) {
		this.therapyRepository = therapyRepository;
	}
	
}
