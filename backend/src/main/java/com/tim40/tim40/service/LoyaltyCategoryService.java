package com.tim40.tim40.service;

import com.tim40.tim40.dto.LoyaltyProgramDTO;
import com.tim40.tim40.model.LoyaltyCategory;
import com.tim40.tim40.model.LoyaltyProgram;
import com.tim40.tim40.model.Patient;
import com.tim40.tim40.model.enums.LoyaltyType;
import com.tim40.tim40.repository.LoyaltyCategoryRepository;
import com.tim40.tim40.repository.LoyaltyProgramRepository;
import com.tim40.tim40.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class LoyaltyCategoryService implements ILoyaltyCategoryService {

    @Autowired
    private LoyaltyCategoryRepository loyaltyCategoryRepository;
    
    @Autowired
    private LoyaltyProgramRepository loyaltyProgramRepository;
    
    @Autowired
    private PatientRepository patients;

    @Override
    public int getMinLoyaltyPoints(LoyaltyType type) {
        LoyaltyCategory loyaltyCategory = getLoyaltyCategoryByType(type);
        return loyaltyCategory.getMinPoints();
    }

    @Override
    public void updateLoyaltyPoints(LoyaltyCategory updatedLoyaltyCategory) {
        if(updatedLoyaltyCategory.getMinPoints() < 0)
            throw new RuntimeException("Loyalty category minimal points can't be lower than 0.");
        LoyaltyCategory loyaltyCategory = getLoyaltyCategoryByType(updatedLoyaltyCategory.getType());

        LoyaltyCategory regularCategory = getLoyaltyCategoryByType(LoyaltyType.REGULAR);
        LoyaltyCategory silverCategory = getLoyaltyCategoryByType(LoyaltyType.SILVER);
        LoyaltyCategory goldCategory = getLoyaltyCategoryByType(LoyaltyType.GOLD);

        if(loyaltyCategory.getType().equals(LoyaltyType.REGULAR) &&
                updatedLoyaltyCategory.getMinPoints() > silverCategory.getMinPoints())
            throw new RuntimeException("Regular category must require less points than silver");
        if(loyaltyCategory.getType().equals(LoyaltyType.SILVER) &&
                updatedLoyaltyCategory.getMinPoints() > goldCategory.getMinPoints() ||  updatedLoyaltyCategory.getMinPoints() < regularCategory.getMinPoints())
            throw new RuntimeException("Silver category must require less points than gold and more than regular");
        if(loyaltyCategory.getType().equals(LoyaltyType.GOLD) && updatedLoyaltyCategory.getMinPoints() < silverCategory.getMinPoints())
            throw new RuntimeException("Gold category must require more points than silver");
        loyaltyCategory.setMinPoints(updatedLoyaltyCategory.getMinPoints());
        loyaltyCategoryRepository.save(loyaltyCategory);
    }

    @Override
    public String getLoyaltyCategoryByPoints(int points) {
        if(points < 0)
            return "No category.";
        List<LoyaltyCategory> categories = loyaltyCategoryRepository.findAll();
        LoyaltyCategory foundCategory = categories.get(0);
        categories.remove(0);
        for(LoyaltyCategory loyaltyCategory: categories) {
            if(loyaltyCategory.getMinPoints() <= points && loyaltyCategory.getMinPoints() <= foundCategory.getMinPoints())
                foundCategory = loyaltyCategory;
        }
        return foundCategory.getType().name();
    }

    @Override
    public LoyaltyCategory getLoyaltyCategoryByType(LoyaltyType loyaltyType) {
        LoyaltyCategory loyaltyCategory = loyaltyCategoryRepository.findLoyaltyCategoryByType(loyaltyType);
        if(loyaltyCategory == null)
            throw new RuntimeException("Loyalty category for this type doesn't exist.");
        else
            return  loyaltyCategory;
    }

	@Override
	public String addLoyalty(LoyaltyProgramDTO lpdto) {
		// TODO Auto-generated method stub
		if(loyaltyProgramRepository.getTipWithPointsOrName(lpdto.getBodovi(), lpdto.getNaziv()) != null) {
			System.out.println("VEC POSTOJI");
			return "NOT OK";
		}
		LoyaltyProgram lp = new LoyaltyProgram();
		lp.setBodovi(lpdto.getBodovi());
		lp.setNaziv(lpdto.getNaziv());
		lp.setPopust(lpdto.getPopust());
		loyaltyProgramRepository.save(lp);
		System.out.println("NE POSTOJ");
		List<Patient> allPatients = patients.findAll();
		Collection<LoyaltyProgram> orderedLoyalty = loyaltyProgramRepository.getAllOrdered();
		for (Patient patient : allPatients) {
			if(patient.getPoints() == null) {
				continue;
			}
			for (LoyaltyProgram loyaltyProgram : orderedLoyalty) {
				if(patient.getPoints() >= loyaltyProgram.getBodovi()) {
					System.out.println("PACIJENT SA NAZIVOM " + patient.getName() + " IMA " + patient.getPoints() + " A LP IMA " + loyaltyProgram.getBodovi());
					patient.setLp(loyaltyProgram);
					break;
				}
			}
		}
		patients.saveAll(allPatients);
		return "OK";
		
		
	}




}
