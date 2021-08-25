package com.tim40.tim40.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tim40.tim40.dto.ComplainDTO;

public interface IComplainService {

	ResponseEntity<List<ComplainDTO>> getNonAnsweredComplains();
}
