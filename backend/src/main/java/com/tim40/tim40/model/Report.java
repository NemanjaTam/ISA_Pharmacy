package com.tim40.tim40.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "reports")
public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(fetch = FetchType.LAZY)
	private final Set<DermatologistAverageRating> dermatologistAvgRating = new HashSet<DermatologistAverageRating>();
	
	@OneToMany(fetch = FetchType.LAZY)
	private final Set<PharmacistAverageRating> pharmacistAvgRating = new HashSet<PharmacistAverageRating>();
	
	

}
