package com.tim40.tim40.model;

import com.tim40.tim40.model.enums.LoyaltyType;

import javax.persistence.*;

@Entity(name = "loyalty_category")
public class LoyaltyCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private LoyaltyType type;

    @Column
    private int minPoints;

    public LoyaltyCategory() {
    }

    public LoyaltyCategory(Long id, LoyaltyType type, int minPoints) {
        this.id = id;
        this.type = type;
        this.minPoints = minPoints;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LoyaltyType getType() {
        return type;
    }

    public void setType(LoyaltyType type) {
        this.type = type;
    }

    public int getMinPoints() {
        return minPoints;
    }

    public void setMinPoints(int minPoints) {
        this.minPoints = minPoints;
    }
}
