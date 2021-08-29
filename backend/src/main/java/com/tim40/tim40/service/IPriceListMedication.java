package com.tim40.tim40.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.tim40.tim40.model.PriceListMedication;
import com.tim40.tim40.model.PriceMedication;

public interface IPriceListMedication {
	public List<PriceListMedication> getActivePriceList(Long pharmacyId);
	public List<PriceListMedication> getAllbyId(Long pharmacyId);
	public List<PriceListMedication> editPriceList(Long id,LocalDateTime date,Set<PriceMedication> prices,Long pharmacyId);
}
