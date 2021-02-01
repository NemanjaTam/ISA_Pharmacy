package tim40.Pharmacy.Model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.security.Timestamp;
import java.util.Collection;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //single table svi su u jednoj
@DiscriminatorColumn(name = "ROLE",discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "PERSONA")
public class Person implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="peron_id",nullable = false, unique = true)
    private long id;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address", referencedColumnName = "address_id", nullable = false)
    private Address address;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(name = "last_changed_password_date")
    private Timestamp passwordResetDate;

    @Column(nullable = false, name="change_password", columnDefinition = "false")
    private boolean mustChangePassword;

    public Person() {
    }

    public Person(long  id, String email, String password, String name, String surname, Address address,
                  String phoneNumber, Timestamp passwordResetDate, boolean mustChangePassword) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.passwordResetDate = passwordResetDate;
        this.mustChangePassword = mustChangePassword;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
//metode za security koje moram override ::pogledati::
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Timestamp getPasswordResetDate() {
        return passwordResetDate;
    }

    public boolean isMustChangePassword() {
        return mustChangePassword;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPasswordResetDate(Timestamp passwordResetDate) {
        this.passwordResetDate = passwordResetDate;
    }

    public void setMustChangePassword(boolean mustChangePassword) {
        this.mustChangePassword = mustChangePassword;
    }
}
