package com.tim40.tim40.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Tuple;
import java.util.stream.Collectors;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tim40.tim40.dto.DermatologistDTO;
import com.tim40.tim40.dto.DermatologistDetailsDTO;
import com.tim40.tim40.dto.DermatologistRatingDTO;
import com.tim40.tim40.model.Dermatologist;
import com.tim40.tim40.model.DermatologistRating;
import com.tim40.tim40.model.Patient;
import com.tim40.tim40.model.Pharmacy;
import com.tim40.tim40.projections.DermatologistDetailsProjection;
import com.tim40.tim40.projections.DermatologistProjection;
import com.tim40.tim40.repository.DermatologistRatingRepository;
import com.tim40.tim40.repository.DermatologistRepository;

@Service
public class DermatologistService implements IDermatologistService {

	private DermatologistRepository dermatologistRepository;
	private DermatologistRatingRepository dermatologistRatingRepository;


	@Autowired
	public DermatologistService(DermatologistRepository dermatologistRepository,DermatologistRatingRepository dermatologistRatingRepository) {
		this.dermatologistRepository = dermatologistRepository;
		this.dermatologistRatingRepository = dermatologistRatingRepository;
	}

	@Override
	public ResponseEntity<Set<Patient>> getAllPatients(Long id) {
		Dermatologist dermatologist = dermatologistRepository.findById(id).get();
		return new ResponseEntity<Set<Patient>>(dermatologist.getAllPatients(), HttpStatus.OK);
	}

	//ne menjati: metoda koja vraca za id farmacije
	@Override
	public ResponseEntity<List<DermatologistProjection>> getAllDermatologists(Long ID) {
	   List<DermatologistProjection> dermatologist = dermatologistRepository.getAllByPharmacyId(ID);
	   return new ResponseEntity<List<DermatologistProjection>>(dermatologist, HttpStatus.OK);
		
	}
	//ne menjati,metoda koja vraca sve moguce dermatologe->projekcija dermatologa
	public ResponseEntity<List<DermatologistDetailsProjection>> getAllDermatologistsForAllPharmacies() {
		   List<DermatologistDetailsProjection> dermatologist = dermatologistRepository.getAllByPharmacyIdWithPharmacyList();
		   return new ResponseEntity<List<DermatologistDetailsProjection>>(dermatologist, HttpStatus.OK);
			
		}
	//ne menjati,metoda koja vraca dermatologe i sve apoteke u kojima rade
	public ResponseEntity<List<DermatologistDetailsDTO>> GetDermatologistsAndPharmacyNames(){
		List<Dermatologist> dermatologists = dermatologistRepository.findAll();
		List<DermatologistDetailsDTO> dtoList = new ArrayList<DermatologistDetailsDTO>();
		for (int i = 0;i < dermatologists.size();i++) {
			DermatologistDetailsDTO derm = new DermatologistDetailsDTO();
			derm.setId(dermatologists.get(i).getId());
			derm.setEmail(dermatologists.get(i).getEmail());
			derm.setName(dermatologists.get(i).getName());
			derm.setSurname(dermatologists.get(i).getSurname());
   		 	List<String> names = new ArrayList<String>();
   		 	for(Pharmacy pharm: dermatologists.get(i).getPharmacies()) {
   		 	 names.add(pharm.getName());
   		 	}
   		 	derm.setNames(names);
   		 	dtoList.add(derm);

   		 	
		}
	return new ResponseEntity<List<DermatologistDetailsDTO>>(dtoList, HttpStatus.OK);
	}
	public List<DermatologistRatingDTO> getRatingsForDermatologists(Long id){
		List<DermatologistRatingDTO> dermatologistsWithRatings = new ArrayList<DermatologistRatingDTO>();
		List<DermatologistRating> ratings = this.dermatologistRatingRepository.findAll();
		for (DermatologistProjection dermatologistProjection : this.dermatologistRepository.getAllByPharmacyId(id)) {
			int rated = 0;
			int size = 0;
			for (DermatologistRating dermatologistRating : ratings) {
				if(dermatologistRating.getDermatologist().getId() == dermatologistProjection.getId()) {
					
					rated = rated + dermatologistRating.getRating();
				
					size = size + 1;				
				}
			}
	    System.out.println(rated);
		double avg = Double.valueOf(rated) / Double.valueOf(size);
		System.out.println(avg);
		System.out.println(size);
		DermatologistRatingDTO dto = new DermatologistRatingDTO(dermatologistProjection.getName(), dermatologistProjection.getSurname(), avg,dermatologistProjection.getEmail(),dermatologistProjection.getPharmacyName());
			dermatologistsWithRatings.add(dto);
		}
		return dermatologistsWithRatings;
	}
}


