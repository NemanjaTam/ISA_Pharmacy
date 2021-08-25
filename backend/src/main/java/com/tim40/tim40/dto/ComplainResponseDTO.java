package com.tim40.tim40.dto;
import com.tim40.tim40.model.Complain;

public class ComplainResponseDTO {

		private Long id;
		private String complain;
		private String response;
		private Long dermatologistId;
		private Long pharmacistId;
		private Long pharmacyId;
		private Long userId;

		public ComplainResponseDTO() { }
		
		public ComplainResponseDTO(Complain complain) {
			this.id = complain.getId();
			this.complain = complain.getComplain();
			this.response = complain.getResponse();
			this.dermatologistId = complain.getDermatologist().getId();
			this.pharmacistId = complain.getPharmacist().getId();
			this.pharmacyId = complain.getPharmacy().getId();
			this.userId = complain.getUser().getId();
		}
		
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getComplain() {
			return complain;
		}
		public void setComplain(String complain) {
			this.complain = complain;
		}
		public Long getDermatologistId() {
			return dermatologistId;
		}
		public void setDermatologistId(Long dermatologistId) {
			this.dermatologistId = dermatologistId;
		}
		public Long getPharmacistId() {
			return pharmacistId;
		}
		public void setPharmacistId(Long pharmacistId) {
			this.pharmacistId = pharmacistId;
		}
		public Long getPharmacyId() {
			return pharmacyId;
		}
		public void setPharmacyId(Long pharmacyId) {
			this.pharmacyId = pharmacyId;
		}
		public String getResponse() {
			return response;
		}

		public void setResponse(String response) {
			this.response = response;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}
	}

