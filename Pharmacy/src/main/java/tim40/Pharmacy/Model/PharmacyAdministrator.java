package tim40.Pharmacy.Model;

import javax.persistence.*;
import java.io.Serializable;

//dodati konstruktor s parametrima i jos podataka o administratoru
//vezu izmedju apoteke i admina
@Entity
@DiscriminatorValue("PHARM_ADMIN")
public class PharmacyAdministrator extends Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="administrator_id",nullable = false, unique = true)
    private long id;

    @ManyToOne
    @JoinColumn(name = "administrated_pharmacy", referencedColumnName = "pharmacy_id", nullable = true)
    private Pharmacy administratedPharmacy;

    public Pharmacy getAdministratedPharmacy() {
        return administratedPharmacy;
    }

    public void setAdministratedPharmacy(Pharmacy administratedPharmacy) { this.administratedPharmacy = administratedPharmacy; }

    public PharmacyAdministrator() {
    }
}
