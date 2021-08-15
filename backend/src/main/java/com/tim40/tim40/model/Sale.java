 package com.tim40.tim40.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tim40.tim40.UnixToLocalDateTimeConverter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Column
    private String freeForm;
    @OneToOne
    private PharmacyAdministrator pharmacyAdmin;
    
//    @ManyToOne
//    @JoinColumn(name = "pharmacy_id", nullable = false)
//    private Pharmacy pharmacy;
	
//    @OneToMany(fetch = FetchType.LAZY)
//    private Set<PriceMedication> saleMedicationPrices = new HashSet<PriceMedication>();
//    
//    @Embedded
//    private Period salePeriod;
	@JsonDeserialize(using = UnixToLocalDateTimeConverter.class)
	@Column(name = "starttime", nullable = true)
	private LocalDate startTime;
	
	@JsonDeserialize(using = UnixToLocalDateTimeConverter.class)
	@Column(name = "endtime", nullable = true)
	private LocalDate endTime;
	
    
	@Column(nullable = false)
	private boolean isActive;
}
