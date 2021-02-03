package tim40.Pharmacy.Model;

import javax.persistence.*;

@Entity
public class Appointment {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "appointment_id", nullable = false, unique = true)
   private long appointmentId;

//  @ManyToOne
//   private Dermatologist dermatologist;

   @Column(nullable = false)
   private double price;

   @Column(nullable = false)
   private Long duration;

   @ManyToOne
   @JoinColumn(name = "appointment_pharmacy", referencedColumnName = "pharmacy_id", nullable = true)
   Pharmacy appointmentPharmacy;

    public Appointment() {
    }

    public long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }

//    public Dermatologist getDermatologist() {
//        return dermatologist;
//    }
//
//    public void setDermatologist(Dermatologist dermatologist) {
//        this.dermatologist = dermatologist;
//    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Pharmacy getAppointmentPharmacy() {
        return appointmentPharmacy;
    }

    public void setAppointmentPharmacy(Pharmacy appointmentPharmacy) {
        this.appointmentPharmacy = appointmentPharmacy;
    }
}
