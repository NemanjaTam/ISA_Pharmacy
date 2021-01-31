package tim40.Pharmacy.Model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.Collection;

public class Person implements Serializable, UserDetails {
    private long id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private Address address;
    private String phoneNumber;
    private Timestamp passwordResetDate;
    private boolean mustChangePassword;

    public Person() {
    }

    public Person(long id, String email, String password, String name, String surname, Address address,
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
