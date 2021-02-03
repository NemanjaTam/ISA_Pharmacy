package tim40.Pharmacy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tim40.Pharmacy.Model.PharmacyAdministrator;

public interface PharmacyAdminRepository extends JpaRepository<PharmacyAdministrator,Long> {
     PharmacyAdministrator findById(long id);
     PharmacyAdministrator findByEmail(String email);

}
