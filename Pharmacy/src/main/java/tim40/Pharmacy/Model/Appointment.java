package tim40.Pharmacy.Model;

public class Appointment {
   private Dermatologist dermatologist;
   private double price;
   //private DateTime dateTimeAppointment; nisam sigurna dal ce ovako biti...
   private int duration;

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public double getPrice() {
        return price;
    }

    public int getDuration() {
        return duration;
    }
}
