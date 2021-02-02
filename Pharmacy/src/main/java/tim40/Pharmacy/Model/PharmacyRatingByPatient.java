package tim40.Pharmacy.Model;

import javax.persistence.*;

@Entity
public class PharmacyRatingByPatient {
    @Id
    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "person_id")
    private Patient patientWhoRates;
    @Id
    @ManyToOne
    @JoinColumn(name = "rated_pharmacy", referencedColumnName = "pharmacy_id")
    private  Pharmacy ratedPharmacy;
    @Column
    private int rating;

    public PharmacyRatingByPatient() {
    }

    public PharmacyRatingByPatient(Patient patientWhoRates, Pharmacy ratedPharmacy, int rating) {
        this.patientWhoRates = patientWhoRates;
        this.ratedPharmacy = ratedPharmacy;
        this.rating = rating;
    }

    public Patient getPatientWhoRates() {
        return patientWhoRates;
    }

    public void setPatientWhoRates(Patient patientWhoRates) {
        this.patientWhoRates = patientWhoRates;
    }

    public Pharmacy getRatedPharmacy() {
        return ratedPharmacy;
    }

    public void setRatedPharmacy(Pharmacy ratedPharmacy) {
        this.ratedPharmacy = ratedPharmacy;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
