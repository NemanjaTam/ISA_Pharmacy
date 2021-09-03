package com.tim40.tim40.dto;

import java.util.Set;

import com.tim40.tim40.model.QuantityMedicationPurchaseOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class dermDTO {
	private Long id;
	private String name;
	private String surname;
	private String email;
	private String pharmacyNames;

}
