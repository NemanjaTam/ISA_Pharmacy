package com.tim40.tim40.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tim40.tim40.dto.AbsenceDTO;
import com.tim40.tim40.model.Absence;
import com.tim40.tim40.repository.AbsenceRepository;

@Service
public class AbsenceService implements IAbsenceService {
	
	private AbsenceRepository absenceRepository;
	
	@Autowired
	public AbsenceService(AbsenceRepository absenceRepository) {
		this.absenceRepository = absenceRepository;
	}

	@Override
	public ResponseEntity<AbsenceDTO> requestAbsence(AbsenceDTO absenceDTO) {
		Absence absence = new Absence(absenceDTO);
		absenceRepository.save(absence);
		return new ResponseEntity<AbsenceDTO>(new AbsenceDTO(absence), HttpStatus.OK);
	}
	
}
