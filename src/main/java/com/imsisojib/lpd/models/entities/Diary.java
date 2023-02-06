package com.imsisojib.lpd.models.entities;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "diaries", uniqueConstraints = {
        @UniqueConstraint(columnNames = "emi")
})
public class Diary {
    @Id
    @Size(min = 15, max = 15)
    private String emi;
    private String deviceName;
    private String modelName;
    private String brand;
    @CreatedDate
    private Timestamp createdDate;
    private Timestamp lostDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_lost_address_id")
    private Address lostAddress;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_owner_id", referencedColumnName = "phoneNumber")
    private User owner;

    public Diary() {
    }

    public Diary(String emi, String deviceName, String modelName, String brand, Timestamp lostDate, Address lostAddress, User owner) {
        this.emi = emi;
        this.deviceName = deviceName;
        this.modelName = modelName;
        this.brand = brand;
        this.lostDate = lostDate;
        this.lostAddress = lostAddress;
        this.owner = owner;
    }

    public String getEmi() {
        return emi;
    }

    public void setEmi(String emi) {
        this.emi = emi;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getLostDate() {
        return lostDate;
    }

    public void setLostDate(Timestamp lostDate) {
        this.lostDate = lostDate;
    }

    public Address getLostAddress() {
        return lostAddress;
    }

    public void setLostAddress(Address lostAddress) {
        this.lostAddress = lostAddress;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
