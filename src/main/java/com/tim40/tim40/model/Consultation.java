package com.tim40.tim40.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "consultations")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Consultation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "report", nullable = false)
	private String report;
	
	@Embedded
	private Period period;
	
	@ManyToOne
    @JoinColumn(name = "pharmacy_id", nullable = false)
    private Pharmacy pharmacy;
	
    @ManyToOne
    @JoinColumn(name = "pharmacist_id", nullable = false)
    private  Pharmacist pharmacist;
    
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "consultation")
    private final Set<Therapy> therapies = new HashSet<Therapy>();
    
}
