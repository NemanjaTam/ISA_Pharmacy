package com.tim40.tim40.controller;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.tim40.tim40.dto.PatientAllergedDTO;
import com.tim40.tim40.dto.PharmacyDTO;
import com.tim40.tim40.dto.PharmacyProfileDTO;
import com.tim40.tim40.model.Medication;
import com.tim40.tim40.model.Pharmacy;
import com.tim40.tim40.service.PharmacyService;
import com.tim40.tim40.util.FileUploadUtil;

@RestController
@RequestMapping(value = "api/pharmacy")
public class PharmacyController {
	
	private PharmacyService pharmacyService;

	@Autowired
	public PharmacyController(PharmacyService pharmacyService) {
		this.pharmacyService = pharmacyService;
	}
	
	@PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addPharmacy(@RequestBody PharmacyDTO pharmacyDTO ) 
	{
		PharmacyDTO createdPharmacy = pharmacyService.createPharmacy(pharmacyDTO);
		return new ResponseEntity<> (createdPharmacy, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-medications/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Medication>> getAllMedications(@PathVariable("id") Long id) {
		return pharmacyService.getAllMedications(id);
	}
	
	@PostMapping(value = "/is-medication-available", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> isMedicationAvailable(@RequestBody PatientAllergedDTO patientAllergedDTO ) 
	{
		return pharmacyService.isMedicationAvailable(patientAllergedDTO.getPatientId(), patientAllergedDTO.getMedicationId());
	}
	
	@GetMapping(value = "/getpharmacy/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public PharmacyProfileDTO getPharmacyById(@PathVariable(name="id") String stringId){
		long id = Long.valueOf(stringId);
        PharmacyProfileDTO pharmacyDTO = new PharmacyProfileDTO();
        Pharmacy pharmacy = pharmacyService.getById(id);
        pharmacyDTO.setName(pharmacy.getName());
        pharmacyDTO.setAvgRating(pharmacy.getAvgRating());
        pharmacyDTO.setAddress(pharmacy.getAddress());

        return pharmacyDTO;
    }

	@PostMapping("/qr-code")
    public ResponseEntity<List<Pharmacy>> saveUser(@RequestParam("image") MultipartFile multipartFile) throws IOException {
         
        String fileName = "qr.jpg";
          
        String uploadDir = "C:\\qr/";
        String path = uploadDir + fileName;
 
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        
        BufferedImage bf = ImageIO.read(new FileInputStream(path));
		
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(
				new BufferedImageLuminanceSource(bf)));
		
		Result result;
		try {
			result = new MultiFormatReader().decode(bitmap);
			
			return pharmacyService.getMedicationByPharmacy(result.getText());
			
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<Pharmacy>>(new ArrayList<Pharmacy>(), HttpStatus.BAD_REQUEST);
    }
}
