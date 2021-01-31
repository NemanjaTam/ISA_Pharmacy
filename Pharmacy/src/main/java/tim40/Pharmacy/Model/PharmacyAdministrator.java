package tim40.Pharmacy.Model;
//dodati konstruktor s parametrima i jos podataka o administratoru
//vezu izmedju apoteke i admina
public class PharmacyAdministrator extends Person {

    private Pharmacy administratedPharmacy;

    public Pharmacy getAdministratedPharmacy() {
        return administratedPharmacy;
    }

    public void setAdministratedPharmacy(Pharmacy administratedPharmacy) {
        this.administratedPharmacy = administratedPharmacy;
    }
//prazan konstruktor, dal treba sa parametrima mozda??
    public PharmacyAdministrator() {
    }
}
