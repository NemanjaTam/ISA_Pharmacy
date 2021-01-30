package tim40.Pharmacy.Model;

import java.util.ArrayList;

public class BusinessReport {
    private double averagePharmacyRating;
    private ArrayList<Double> averagePharmacistRatings;
    private ArrayList<Double> averageDermatologistRatings;

    public double getAveragePharmacyRating() {
        return averagePharmacyRating;
    }

    public ArrayList<Double> getAveragePharmacistRatings() {
        return averagePharmacistRatings;
    }

    public ArrayList<Double> getAverageDermatologistRatings() {
        return averageDermatologistRatings;
    }

    public void setAveragePharmacyRating(double averagePharmacyRating) {
        this.averagePharmacyRating = averagePharmacyRating;
    }

    public void setAveragePharmacistRatings(ArrayList<Double> averagePharmacistRatings) {
        this.averagePharmacistRatings = averagePharmacistRatings;
    }

    public void setAverageDermatologistRatings(ArrayList<Double> averageDermatologistRatings) {
        this.averageDermatologistRatings = averageDermatologistRatings;
    }


}
