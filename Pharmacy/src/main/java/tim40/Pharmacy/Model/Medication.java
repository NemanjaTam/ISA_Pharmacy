package tim40.Pharmacy.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

enum MedicationShape
{
    PRASAK, KAPSULA, TABLETA, MAST, PASTA, GEL, RASTVOR, SIRUP;
}
enum PublicationIssue {
    NA_RECEPT,BEZ_RECEPTA;
}

@Entity
public class Medication implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="medication_id",nullable = false, unique = true)
    private long id;

    @Column(name = "medication_name",nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private MedicationShape medicationShape;

    @Column(nullable = false)
    private PublicationIssue publicationIssue;

    @Column(nullable = false)
    private String ingredients;

    @Column(nullable = false)
    private String manufacturer;

     //OVO TREBA DRUGACIJE-> KAKO CE SE PRIKAZATI U BAZI?
    private List<Long> alternativeMedicationIds = new ArrayList<Long>();

    @Column(nullable = false)
    private String additionalInfo;

    public Medication() {

    }

    public Medication(long id, String name, String type, MedicationShape medicationShape, PublicationIssue publicationIssue,
                      String ingredients, String manufacturer,
                      List<Long> alternativeMedicationIds, String additionalInfo) {
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

    public long getId() {
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

    public List<Long> getAlternativeMedicationIds() {
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

    public void setAlternativeMedicationIds(List<Long> alternativeMedicationIds) {
        this.alternativeMedicationIds = alternativeMedicationIds;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
