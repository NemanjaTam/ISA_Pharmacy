package com.tim40.tim40.model;

import javax.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="price_consultations")
public class PriceConsultation {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column(nullable = false)
	private double priceAppointment; 
	
	@ManyToOne  //lek moze da ima vise cena ali cena-stavka cenovnika moze imati samo jedan lek
	@JoinColumn(name = "pharmacist_id", nullable = false)  //unidirekciona veza
	private Pharmacist pharmacist; 

	@ManyToOne //PROMENITI OVO
	@JoinColumn(name = "price_list_consultation_id", nullable = false)
	private PriceListConsultation priceListConsultation;
}
