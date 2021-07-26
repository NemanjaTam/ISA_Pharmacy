package com.tim40.tim40.service;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;

public interface IWorkDayService {
	ResponseEntity<String> checkIsUserWorking(LocalDateTime  date, Long userId);
}
