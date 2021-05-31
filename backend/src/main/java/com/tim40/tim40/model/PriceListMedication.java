package com.tim40.tim40.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "price_list_medications")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PriceListMedication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private boolean active;
 
	@Embedded
	private Period durationPeriod;
	
	
	@OneToMany(fetch = FetchType.LAZY)
	private Set<PriceMedication> medicationPrices = new HashSet<PriceMedication>();
	
	@ManyToOne
    @JoinColumn(name = "pharmacy_id", nullable = false)
	private Pharmacy pharmacy;

}
