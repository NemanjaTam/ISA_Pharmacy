package com.tim40.tim40.repository;

import com.tim40.tim40.model.LoyaltyCategory;
import com.tim40.tim40.model.enums.LoyaltyType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoyaltyCategoryRepository extends JpaRepository<LoyaltyCategory, Long> {
    LoyaltyCategory findLoyaltyCategoryByType(LoyaltyType type);
}
