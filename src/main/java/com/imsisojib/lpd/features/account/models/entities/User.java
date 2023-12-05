package com.imsisojib.lpd.features.account.models.entities;

import com.imsisojib.lpd.features.geocodes.models.entities.Address;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "phoneNumber")
})
public class User {
    @Id
    @Size(min = 11, max = 11)
    private String phoneNumber;
    private String name;
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(  name = "fk_address_id", referencedColumnName = "id")
    private Address address;

    public User() {
    }

    public User(String phoneNumber, String name, String email, Address address) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
