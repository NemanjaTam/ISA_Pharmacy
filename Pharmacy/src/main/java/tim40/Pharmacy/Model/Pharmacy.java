package tim40.Pharmacy.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
//KONSTUKTOR S PARAMETRIMA KADA SE DODAJU OSTALI CLANOVI KLASE
//DODATI I VEZE KA NJIMA OBAVEZNO
//OTKOMENTARISATI GETTERE I SETTERE
@Entity
public class Pharmacy implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generisu se
    @Column(name ="pharmacy_id",nullable = false, unique = true)
    private long pharmacyId;

    @Column(nullable = false)
    private String namePharmacy;
    //dodati veze ovde jer je tip kompleksan
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "addressPharmacy", referencedColumnName = "address_id", nullable = false)
    private Address addressPharmacy;

    @Column(nullable = false)
    private String descriptionPharmacy;

//    @OneToMany(mappedBy = "", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Dermatologist> dermatologists = new ArrayList<>();
//
//    @OneToMany(mappedBy = "", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Pharmacist> pharmacists = new ArrayList<>();
//
//    @OneToMany(mappedBy = "", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Medication> medicationList = new ArrayList<>();
//
    @OneToMany(mappedBy = "appointment_pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Appointment> availableDermatologistAppointments = new ArrayList<>();

    //nasledjivanje
    @OneToMany(mappedBy = "administrated_pharmacy",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PharmacyAdministrator> pharmacyAdministrators = new ArrayList<>();
   // private List<PriceList> priceListMedicines = new ArrayList<>();

    public Pharmacy() {
    }

    public long getPharmacyIdId() {
        return pharmacyId;
    }

    public void setPharmacyIdId(long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public String getNamePharmacy() {
        return namePharmacy;
    }

    public void setNamePharmacy(String namePharmacy) {
        this.namePharmacy = namePharmacy;
    }

    public Address getAddressPharmacy() {
        return addressPharmacy;
    }

    public void setAddressPharmacy(Address addressPharmacy) {
        this.addressPharmacy = addressPharmacy;
    }

    public String getDescriptionPharmacy() {
        return descriptionPharmacy;
    }

    public void setDescriptionPharmacy(String descriptionPharmacy) {
        this.descriptionPharmacy = descriptionPharmacy;
    }

//    public List<Dermatologist> getDermatologists() {
//        return dermatologists;
//    }
//
//    public void setDermatologists(List<Dermatologist> dermatologists) {
//        this.dermatologists = dermatologists;
//    }
//
//    public List<Pharmacist> getPharmacists() {
//        return pharmacists;
//    }
//
//    public void setPharmacists(List<Pharmacist> pharmacists) {
//        this.pharmacists = pharmacists;
//    }
//
//    public List<Medicine> getMedicines() {
//        return medicines;
//    }
//
//    public void setMedicines(List<Medicine> medicines) {
//        this.medicines = medicines;
//    }
//
//    public List<Appointment> getAvailableDermatologistAppointments() {
//        return availableDermatologistAppointments;
//    }
//
//    public void setAvailableDermatologistAppointments(List<Appointment> availableDermatologistAppointments) {
//        this.availableDermatologistAppointments = availableDermatologistAppointments;
//    }
//
//    public List<PriceList> getPriceListMedicines() {
//        return priceListMedicines;
//    }
//
//    public void setPriceListMedicines(List<PriceList> priceListMedicines) {
//        this.priceListMedicines = priceListMedicines;
//    }
}