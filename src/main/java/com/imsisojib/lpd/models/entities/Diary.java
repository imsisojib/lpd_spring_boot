package com.imsisojib.lpd.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Date;

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
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createdDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date lostDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_lost_address_id")
    private Address lostAddress;

    @Column(name = "fk_owner_id")
    private String ownerId;

    public Diary() {
    }

    public Diary(String emi, String deviceName, String modelName, String brand, Date lostDate, Address lostAddress, String ownerId) {
        this.emi = emi;
        this.deviceName = deviceName;
        this.modelName = modelName;
        this.brand = brand;
        this.lostDate = lostDate;
        this.lostAddress = lostAddress;
        this.ownerId = ownerId;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLostDate() {
        return lostDate;
    }

    public void setLostDate(Date lostDate) {
        this.lostDate = lostDate;
    }

    public Address getLostAddress() {
        return lostAddress;
    }

    public void setLostAddress(Address lostAddress) {
        this.lostAddress = lostAddress;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String owner) {
        this.ownerId = owner;
    }
}
