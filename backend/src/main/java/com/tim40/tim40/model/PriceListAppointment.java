package com.tim40.tim40.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import lombok.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="price_list_appointments")
public class PriceListAppointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private boolean active;
 
	@Embedded
	private Period durationPeriod;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "priceListAppointment")
	private Set<PriceAppointment> appointmentPrices = new HashSet<PriceAppointment>();
	
	@ManyToOne
    @JoinColumn(name = "pharmacy_id", nullable = false)
	private Pharmacy pharmacy;
}
