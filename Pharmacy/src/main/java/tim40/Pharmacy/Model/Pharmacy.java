package tim40.Pharmacy.Model;

import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

public class Pharmacy implements Serializable {

    private String namePharmacy;
    private Address addressPharmacy;
    private String descriptionPharmacy;
    //  private List<Dermatologist> dermatologists;
    //  private List<Pharmacist> pharmacists;
    //  private List<Medicine> medicines;
    //private List<Appointment> availableDermatologistAppointments;
    // private List<PriceList> priceListMedicines;
    @OneToMany
    private List<PharmacyAdministrator> pharmacyAdministrators;
    public Pharmacy() {
    }





    public String getNamePharmacy() {
        return namePharmacy;
    }
    public String getDescriptionPharmacy() {
        return descriptionPharmacy;
    }

    public Address getAddressPharmacy() {
        return addressPharmacy;
    }


    public List<PharmacyAdministrator> getPharmacyAdministrators() {
        return pharmacyAdministrators;
    }
    //    public ArrayList<Dermatologist> getDermatologists() {
//        return dermatologists;
//    }
//
//    public List<Pharmacist> getPharmacists() {
//        return pharmacists;
//    }
//
//    public List<Medicine> getMedicines() {
//        return medicines;
//    }
//
//    public List<Appointment> getAvailableDermatologistAppointments() {
//        return availableDermatologistAppointments;
//    }
//
//    public List<PriceList> getPriceListMedicines() {
//        return priceListMedicines;
//    }
//
//    public List<PriceList> getPriceListAppointments() {
//        return priceListAppointments;
//    }


    public void setNamePharmacy(String namePharmacy) {
        this.namePharmacy = namePharmacy;
    }


    public void setDescriptionPharmacy(String descriptionPharmacy) {
        this.descriptionPharmacy = descriptionPharmacy;
    }

    public void setAddressPharmacy(Address addressPharmacy) {
        this.addressPharmacy = addressPharmacy;
    }

    public void setPharmacyAdministrators(List<PharmacyAdministrator> pharmacyAdministrators) {
        this.pharmacyAdministrators = pharmacyAdministrators;
    }

    //    public void setDermatologists(ArrayList<Dermatologist> dermatologists) {
//        this.dermatologists = dermatologists;
//    }
//
//    public void setPharmacists(ArrayList<Pharmacist> pharmacists) {
//        this.pharmacists = pharmacists;
//    }
//
//    public void setMedicines(ArrayList<Medicine> medicines) {
//        this.medicines = medicines;
//    }
//
//    public void setAvailableDermatologistAppointments(ArrayList<Appointment> availableDermatologistAppointments) {
//        this.availableDermatologistAppointments = availableDermatologistAppointments;
//    }
//
//    public void setPriceListMedicines(List<PriceList> priceListMedicines) {
//        this.priceListMedicines = priceListMedicines;
//    }
//
//    public void setPriceListAppointments(List<PriceList> priceListAppointments) {
//        this.priceListAppointments = priceListAppointments;
//    }
//
//    private List<PriceList> priceListAppointments;
//
//}
}