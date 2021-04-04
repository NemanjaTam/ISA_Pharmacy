package com.tim40.tim40.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim40.tim40.repository.ConsultationRepository;

@Service
public class ConsultationService implements IConsultationService {
	
	private ConsultationRepository consultationRepository;

	@Autowired
	public ConsultationService(ConsultationRepository consultationRepository) {
		this.consultationRepository = consultationRepository;
	}
	
}
