package com.tim40.tim40.service;

import com.tim40.tim40.dto.LoyaltyProgramDTO;
import com.tim40.tim40.model.LoyaltyCategory;
import com.tim40.tim40.model.enums.LoyaltyType;

public interface ILoyaltyCategoryService {
    int getMinLoyaltyPoints(LoyaltyType type);
    void updateLoyaltyPoints(LoyaltyCategory updatedLoyaltyCategory);
    String getLoyaltyCategoryByPoints(int points);
    LoyaltyCategory getLoyaltyCategoryByType(LoyaltyType loyaltyType);
    String addLoyalty(LoyaltyProgramDTO lpdto);

}
