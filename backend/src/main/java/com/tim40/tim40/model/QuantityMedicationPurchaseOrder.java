package com.tim40.tim40.model;
import javax.persistence.*;
import lombok.*;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="medication_quantity_of_purchase_order")
@Getter
@Setter
//ova klasa se koristi za purchase order iskljucivo jer ne sme da se stavlja u quantity medication lek koji je porucen ali nije NABAVLJEN!	
public class QuantityMedicationPurchaseOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private int quantity;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Medication medication;
	

}
