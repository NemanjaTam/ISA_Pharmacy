package tim40.Pharmacy.Model;

import java.util.ArrayList;

enum MedicationShape
{
    PRASAK, KAPSULA, TABLETA, MAST, PASTA, GEL, RASTVOR, SIRUP;
}
enum PublicationIssue {
    NA_RECEPT,BEZ_RECEPTA;
}


public class Medication {
    private int id;
    private String name;
    private String type;
    private MedicationShape medicationShape;
    private PublicationIssue publicationIssue;
    private String ingredients;
    private String manufacturer;
    private ArrayList<Integer> alternativeMedicationIds;
    private String additionalInfo;

    public Medication() {

    }

    public Medication(int id, String name, String type, MedicationShape medicationShape, PublicationIssue publicationIssue,
                      String ingredients, String manufacturer,
                      ArrayList<Integer> alternativeMedicationIds, String additionalInfo) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.medicationShape = medicationShape;
        this.publicationIssue = publicationIssue;
        this.ingredients = ingredients;
        this.manufacturer = manufacturer;
        this.alternativeMedicationIds = alternativeMedicationIds;
        this.additionalInfo = additionalInfo;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public MedicationShape getMedicationShape() {
        return medicationShape;
    }

    public PublicationIssue getPublicationIssue() {
        return publicationIssue;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public ArrayList<Integer> getAlternativeMedicationIds() {
        return alternativeMedicationIds;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMedicationShape(MedicationShape medicationShape) {
        this.medicationShape = medicationShape;
    }

    public void setPublicationIssue(PublicationIssue publicationIssue) {
        this.publicationIssue = publicationIssue;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setAlternativeMedicationIds(ArrayList<Integer> alternativeMedicationIds) {
        this.alternativeMedicationIds = alternativeMedicationIds;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
