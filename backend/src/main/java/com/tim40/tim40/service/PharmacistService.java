package com.tim40.tim40.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tim40.tim40.dto.DermatologistRatingDTO;
import com.tim40.tim40.dto.PharmacistRatingDTO;
import com.tim40.tim40.model.Dermatologist;
import com.tim40.tim40.model.DermatologistRating;
import com.tim40.tim40.model.Patient;
import com.tim40.tim40.model.Pharmacist;
import com.tim40.tim40.model.PharmacistRating;
import com.tim40.tim40.projections.DermatologistProjection;
import com.tim40.tim40.projections.PharmacistDetailsProjection;
import com.tim40.tim40.projections.PharmacistProjection;
import com.tim40.tim40.repository.PharmacistRatingRepository;
import com.tim40.tim40.repository.PharmacistRepository;

@Service
public class PharmacistService implements IPharmacistService {

	private PharmacistRepository pharmacistRepository;
	private PharmacistRatingRepository pharmacistRatingRepository;

	@Autowired
	public PharmacistService(PharmacistRepository pharmacistRepository,PharmacistRatingRepository pharmacistRatingRepository) {
		this.pharmacistRepository = pharmacistRepository;
		this.pharmacistRatingRepository = pharmacistRatingRepository;
	}

	@Override
	public ResponseEntity<Set<Patient>> getAllPatients(Long id) {
		Pharmacist pharmacist = pharmacistRepository.findById(id).get();
		return new ResponseEntity<Set<Patient>>(pharmacist.getAllPatients(), HttpStatus.OK);
	}

  //preko idja farmacije: ne menjati
	@Override
	public ResponseEntity<List<PharmacistDetailsProjection>> getAllPharamcistsById(Long ID) {
		return new ResponseEntity<List<PharmacistDetailsProjection>>(pharmacistRepository.getAllByPharmacyId(ID,"PHARMACIST"), HttpStatus.OK);
	}
	//vraca sve farmaceute: ne menjati
	@Override
	public ResponseEntity<List<PharmacistDetailsProjection>> getAllPharamcists() {
		return new ResponseEntity<List<PharmacistDetailsProjection>>(pharmacistRepository.getAllPharmacist("PHARMACIST"), HttpStatus.OK);
	}
	
	
	  //pomocna metoda: ne menjati
	@Override
	public ResponseEntity<List<PharmacistDetailsProjection>> getAllPharmacistData(Long ID) {
		return new ResponseEntity<List<PharmacistDetailsProjection>>(pharmacistRepository.getAllByPharmacyId(ID,"PHARMACIST"), HttpStatus.OK);
	}
	  //pomocna metoda: ne menjati
	@Override
	public Long getPharmacy(Long id) {
		Pharmacist pharmacist = pharmacistRepository.findById(id).get();
		return pharmacist.getPharmacy().getId();
//		return null;
	}

	@Override
	public ResponseEntity<List<PharmacistDetailsProjection>> getAllPharamcistsNEW(Long ID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public List<PharmacistRatingDTO> getPharmacistRatings(Long id,String type){
		List<PharmacistRatingDTO> dermatologistsWithRatings = new ArrayList<PharmacistRatingDTO>();
		List<PharmacistRating> ratings = this.pharmacistRatingRepository.findAll();
		for (PharmacistDetailsProjection dermatologistProjection : this.pharmacistRepository.getAllByPharmacyId(id, type)) {
			int rated = 0;
			int size = 0;
			for (PharmacistRating dermatologistRating : ratings) {
				if(dermatologistRating.getPharmacist().getId() == dermatologistProjection.getId()) {
					
					rated = rated + dermatologistRating.getRating();
				
					size = size + 1;				
				}
			}
	    System.out.println(rated);
		double avg = Double.valueOf(rated) / Double.valueOf(size);
		System.out.println(avg);
		System.out.println(size);
		PharmacistRatingDTO dto = new PharmacistRatingDTO(dermatologistProjection.getName(), dermatologistProjection.getSurname(), avg,dermatologistProjection.getEmail(),dermatologistProjection.getPharmacyName());
			dermatologistsWithRatings.add(dto);
		}
		return dermatologistsWithRatings;
	}
	
}
