package com.tim40.tim40.model;
import javax.persistence.*;
import com.tim40.tim40.model.enums.UserType;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DiscriminatorValue(value = "Pharmacy_administrator")
public class PharmacyAdministrator extends User{


	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_id", nullable = true)
	private Pharmacy pharmacy;



}
