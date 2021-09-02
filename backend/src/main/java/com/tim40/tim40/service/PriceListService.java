package com.tim40.tim40.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim40.tim40.model.PriceListMedication;
import com.tim40.tim40.model.PriceMedication;
import com.tim40.tim40.model.enums.PriceListType;
import com.tim40.tim40.repository.PharmacyRepository;
import com.tim40.tim40.repository.PriceListMedicationRepository;

@Service
public class PriceListService implements IPriceListMedication{

	private PriceListMedicationRepository priceListRepository;
	private PharmacyRepository pharmacyRepository;
	

	@Autowired
	public PriceListService(PriceListMedicationRepository priceListRepository,PharmacyRepository pharmacyRepository) {
		this.priceListRepository = priceListRepository;
		this.pharmacyRepository = pharmacyRepository;
	}
	
	@Override
	public List<PriceListMedication> getActivePriceList(Long pharmacyId) {
	    List<PriceListMedication> list = this.priceListRepository.getAllByPharmacyId(pharmacyId);
	    List<PriceListMedication> active = new ArrayList<PriceListMedication>();
	    for (PriceListMedication priceListMedication : list) {
			if(priceListMedication.getStatus().equals(PriceListType.ACTIVE)) {
				active.add(priceListMedication);
				return active;
			}
		}
		return null;
	}

	@Override
	public List<PriceListMedication> getAllbyId(Long pharmacyId) {
		return this.priceListRepository.getAllByPharmacyId(pharmacyId);
		
	}

	@Override
	public List<PriceListMedication> editPriceList(Long id, LocalDateTime date,Set<PriceMedication> prices,Long pharmacyId) {
		LocalDateTime now = LocalDateTime.now();
		PriceListMedication newPriceList = new PriceListMedication();
		newPriceList.setMedicationPrices(prices);
		newPriceList.setStartTime(date);

		List<PriceListMedication> allPriceLists = this.priceListRepository.getAllByPharmacyId(pharmacyId);
		for (PriceListMedication priceListMedication : allPriceLists) {
			if(priceListMedication.getStatus().equals(PriceListType.ACTIVE)) {
				if(priceListMedication.getStartTime().isBefore(date)) {
					if(date.equals(now) || date.isBefore(now)) {
						newPriceList.setStatus(PriceListType.ACTIVE);
						priceListMedication.setStatus(PriceListType.INACTIVE);
						this.priceListRepository.save(priceListMedication);
						System.out.println(date + "datum koji sam unela");
						System.out.println(now + "datum koji je sad");
					}else {
						newPriceList.setStatus(PriceListType.NOT_YET_ACTIVE);
					}
					
				}else {
					newPriceList.setStatus(PriceListType.NOT_YET_ACTIVE);
					System.out.println("usao u ovo");
				}
			}
		}
		newPriceList.setPharmacy(this.pharmacyRepository.getById(pharmacyId));
		this.priceListRepository.save(newPriceList);
		List<PriceListMedication> list = this.getActivePriceList(pharmacyId);
		
		
		return list;
	}

}
