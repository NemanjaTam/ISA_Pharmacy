package com.tim40.tim40.dto;

import com.tim40.tim40.model.enums.LoyaltyType;

public class LoyaltyCategoryDTO {
    private LoyaltyType type;
    private int points;

    public LoyaltyCategoryDTO() {
    }

    public LoyaltyCategoryDTO(LoyaltyType type, int points) {
        this.type = type;
        this.points = points;
    }

    public LoyaltyType getType() {
        return type;
    }

    public void setType(LoyaltyType type) {
        this.type = type;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
