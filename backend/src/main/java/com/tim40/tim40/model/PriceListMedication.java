package com.tim40.tim40.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tim40.tim40.UnixToLocalDateTimeConverter;
import com.tim40.tim40.model.enums.PriceListType;

import lombok.*;

@Entity
@Table(name = "price_list_medications")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PriceListMedication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private PriceListType status;
 
//	@Embedded
//	private Period durationPeriod;
	@Column(nullable = false)
//	@JsonDeserialize(using = UnixToLocalDateTimeConverter.class)

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startTime; //od kad vazi, vazi dok ne promenim 
	
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="priceListMedication")
	private Set<PriceMedication> medicationPrices = new HashSet<PriceMedication>();
	
	@ManyToOne
	@JsonIgnore
    @JoinColumn(name = "pharmacy_id", nullable = false)
	private Pharmacy pharmacy;

}
