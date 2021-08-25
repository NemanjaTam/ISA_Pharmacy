package com.tim40.tim40.model;
import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "price_medications")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PriceMedication {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column(nullable = false)
	private float price; 
	
	@ManyToOne  //lek moze da ima vise cena ali cena-stavka cenovnika moze imati samo jedan lek
	@JoinColumn(name = "medication_id", nullable = false)  //unidirekciona veza
	private Medication medication; 

	@ManyToOne //vlasnik veze ima i strani kljuc
	@JoinColumn(name = "price_list_medication_id", nullable = false)
	private PriceListMedication priceListMedication;
		
	
}
