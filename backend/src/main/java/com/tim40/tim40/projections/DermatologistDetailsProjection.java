package com.tim40.tim40.projections;

import java.util.List;

public interface DermatologistDetailsProjection {
	 Long getId();
	  String getName();
	  String getSurname();
	  String getEmail();
	  List<String> getPharmaciesNames(); 
}
