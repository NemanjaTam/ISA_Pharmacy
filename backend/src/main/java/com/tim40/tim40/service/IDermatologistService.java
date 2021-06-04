package com.tim40.tim40.service;

import com.tim40.tim40.dto.DermatologistDTO;

public interface IDermatologistService {
	DermatologistDTO getDermatologistByEmail(String email);
}
