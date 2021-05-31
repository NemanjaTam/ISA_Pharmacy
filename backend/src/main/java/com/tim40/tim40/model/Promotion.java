package com.tim40.tim40.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Promotion {
    @Id
	private Long id;
	
    @OneToMany(fetch = FetchType.LAZY)
    private Set<PriceMedication> saleMedicationPrices = new HashSet<PriceMedication>();
    
    @Embedded
    private Period salePeriod;
    
	@Column(nullable = false)
	private boolean isActive;
}
