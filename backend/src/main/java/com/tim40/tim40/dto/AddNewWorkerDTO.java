package com.tim40.tim40.dto;

import java.util.Set;

import com.tim40.tim40.model.Address;
import com.tim40.tim40.model.QuantityMedicationPurchaseOrder;
import com.tim40.tim40.model.enums.UserType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AddNewWorkerDTO {
	private String name;

	private String surname;
	
	private String email;

	private String password;

	private Address address;
	
	private String phone;
	
	private UserType userType;
	
	private boolean isFirstTimeLogging;

}
