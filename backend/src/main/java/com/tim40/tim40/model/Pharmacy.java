package com.tim40.tim40.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pharmacies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pharmacy {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Version 
	private Long version;

    @Column(nullable = false)
    private String name;

    @Embedded
    private Address address;

    @Column(name = "avg_rating", nullable = false)
    private double avgRating;
    
    @ManyToMany
    @JoinTable
    (
        name = "pharmacy_suppliers",
        joinColumns = @JoinColumn(name = "pharmacy_id"),
        inverseJoinColumns = @JoinColumn(name = "supplier_id")
    )
	private final Set<Supplier> suppliers = new HashSet<Supplier>();

	@ManyToMany(mappedBy = "subscriptions")
    private final Set<User> subscribedUsers = new HashSet<User>();

//    @ManyToMany
//    @JoinTable
//    (
//        name = "dermatologist_pharmacy",
//        joinColumns = @JoinColumn(name = "pharmacy_id"),
//        inverseJoinColumns = @JoinColumn(name = "dermatologist_id")
//    )
//    private final Set<Dermatologist> dermatologists = new HashSet<Dermatologist>();

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pharmacy")
//    private final Set<Pharmacist> pharmacists = new HashSet<Pharmacist>();
    
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pharmacy")
//    private final Set<Consultation> consultations = new HashSet<Consultation>();

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pharmacy")
//    private final Set<Appointment> appointments = new HashSet<Appointment>();

//    @OneToMany(fetch = FetchType.LAZY)
//    private final Set<QuantityMedication> medications = new HashSet<QuantityMedication>();

//    @OneToMany(fetch = FetchType.LAZY,mappedBy="pharmacy")
//	  private final Set<PharmacyAdministrator> pharmacyAdministrators = new HashSet<PharmacyAdministrator>();

//    @OneToMany(fetch = FetchType.LAZY,mappedBy="pharmacy")
//	private final Set<PriceListMedication> priceListMedication = new HashSet<PriceListMedication>();
//
//    @OneToMany(fetch = FetchType.LAZY,mappedBy="pharmacy")
//	private final Set<PriceListAppointment> priceListAppointment = new HashSet<PriceListAppointment>();
//
//    @OneToMany(fetch = FetchType.LAZY,mappedBy="pharmacy")
//	private final Set<PriceListConsultation> priceListConsultation = new HashSet<PriceListConsultation>();
//    
//    @OneToMany(fetch = FetchType.LAZY,mappedBy="pharmacy")
//    private Set<PharmacyRating> ratings = new HashSet<PharmacyRating>();

    @ManyToMany(fetch = FetchType.LAZY,mappedBy="pharmacy",cascade = CascadeType.ALL)
   	private final Set<QuantityMedication> medicationQuantity = new HashSet<QuantityMedication>();
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy="pharmacy",cascade = CascadeType.ALL)
   	private final Set<PurchaseOrder> purchaseOrders = new HashSet<PurchaseOrder>(); 
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy="pharmacy",cascade = CascadeType.ALL)
   	private final Set<Absence> absences = new HashSet<Absence>(); 
    
//    @OneToMany(fetch = FetchType.LAZY,mappedBy="pharmacy")
//   	private final Set<Sale> sales = new HashSet<Sale>();
//    
    @OneToMany(fetch = FetchType.LAZY)
    private final Set<User> subscribers = new HashSet<User>();
    
    
    public Pharmacy(String name, Address address)
    {
        this.name = name;
        this.address = address;
        this.avgRating = 0.0;
    }
    //ne menjati
    public List<Medication> getAllMedication() {
    	List<Medication> medication = new ArrayList<Medication>();
    	for (QuantityMedication quantityMedication : medicationQuantity) {
			medication.add(quantityMedication.getMedication());
		}
		return medication;
    }
    
    public PurchaseOrder update(PurchaseOrder po) {
    	for (PurchaseOrder purchaseOrder : purchaseOrders) {
			if(po.getId().equals(purchaseOrder.getId())) {
			    purchaseOrder.setOffers(po.getOffers());
			    purchaseOrder.setPurchaseOrderStatus(po.getPurchaseOrderStatus());
			}
		}
		return po;
    	
    }
    

}
