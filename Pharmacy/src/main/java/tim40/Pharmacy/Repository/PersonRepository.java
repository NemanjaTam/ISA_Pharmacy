package tim40.Pharmacy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tim40.Pharmacy.Model.Person;

public interface PersonRepository extends JpaRepository<Person,Long> {

    Person findById(long id);
    Person findByEmail(String email);
    Person findByPhoneNumber(String phoneNumber);
}
