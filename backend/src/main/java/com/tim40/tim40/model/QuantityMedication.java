package com.tim40.tim40.model;

import javax.persistence.*;
import lombok.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="medication_quantity")
@Getter
@Setter
public class QuantityMedication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private int quantity;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Medication medication;
	
	@ManyToOne
    @JoinColumn(name = "pharmacy_id", nullable = false)
	private Pharmacy pharmacy;
	
	@Version
	private Long version;
}
