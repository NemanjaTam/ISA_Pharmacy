package tim40.Pharmacy.Repository;

import org.springframework.data.jpa.repository.*;
import tim40.Pharmacy.Model.Pharmacy;

public interface PharmacyRepository extends JpaRepository<Pharmacy,Long> {

    Pharmacy findById(long id);
    Pharmacy findByName(String name);
    @Modifying
    Pharmacy save(Pharmacy pharm);
}
