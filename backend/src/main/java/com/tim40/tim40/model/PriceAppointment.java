package com.tim40.tim40.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="price_appointments")
public class PriceAppointment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column(nullable = false)
	private double priceAppointment; 
	
	@ManyToOne  //lek moze da ima vise cena ali cena-stavka cenovnika moze imati samo jedan lek
	@JoinColumn(name = "dermatologist_id", nullable = false)  //unidirekciona veza
	private Dermatologist dermatologist; 

	@ManyToOne //PROMENITI OVO
	@JoinColumn(name = "price_list_appointment_id", nullable = false)
	private PriceListAppointment priceListAppointment;
}
