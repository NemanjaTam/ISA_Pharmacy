package com.tim40.tim40.model;

import java.util.*;
import java.util.Set;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="price_list_consultations")
public class PriceListConsultation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private boolean active;
 
	@Embedded
	private Period durationPeriod;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "priceListConsultation")
	private Set<PriceConsultation> consultationPrices = new HashSet<PriceConsultation>();
	
	@ManyToOne
    @JoinColumn(name = "pharmacy_id", nullable = false)
	private Pharmacy pharmacy;
}
