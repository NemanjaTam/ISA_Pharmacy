package com.tim40.tim40.model;

import java.time.LocalDate;
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
 
//	@Embedded
//	private Period durationPeriod;
	@Column(nullable = false)
	private LocalDate startTime; //od kad vazi, vazi dok ne promenim 
	
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="priceListMedication")
	private Set<PriceMedication> medicationPrices = new HashSet<PriceMedication>();
	
	@ManyToOne
    @JoinColumn(name = "pharmacy_id", nullable = false)
	private Pharmacy pharmacy;

}
