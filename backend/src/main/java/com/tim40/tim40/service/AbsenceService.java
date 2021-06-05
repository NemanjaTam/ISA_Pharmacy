package com.tim40.tim40.service;

import java.time.LocalDateTime;
import java.util.List;

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

	@Override
	public ResponseEntity<String> checkIsUserOnAbsence(LocalDateTime date,Long userId) {
		List<Absence> absences = absenceRepository.findAll();
		for(Absence absence : absences) {
			if(absence.isApproved() && !absence.isFinished() && absence.getUser().getId() == userId) {
				if(absence.getPeriod().getStartTime().compareTo(date) <= 0 && absence.getPeriod().getEndTime().compareTo(date) >= 0) {
					return new ResponseEntity<String>("on_absence", HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<String>("not_on_absence", HttpStatus.OK);
	}
	
}
