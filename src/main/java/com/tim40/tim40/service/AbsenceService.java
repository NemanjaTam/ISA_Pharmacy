package com.tim40.tim40.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim40.tim40.repository.AbsenceRepository;

@Service
public class AbsenceService implements IAbsenceService {
	
	private AbsenceRepository absenceRepository;
	
	@Autowired
	public AbsenceService(AbsenceRepository absenceRepository) {
		this.absenceRepository = absenceRepository;
	}
	
}
