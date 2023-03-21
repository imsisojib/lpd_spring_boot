package com.imsisojib.lpd.models.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.imsisojib.lpd.models.entities.Address;
import com.imsisojib.lpd.models.entities.User;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Date;

public class RequestDiaryBody {

    private String emi;
    private String deviceName;
    private String modelName;
    private String brand;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date lostDate;
    private Address lostAddress;

    private String userId;



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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public RequestDiaryBody(String emi, String deviceName, String modelName, String brand, Date lostDate, Address lostAddress, String userId) {
        this.emi = emi;
        this.deviceName = deviceName;
        this.modelName = modelName;
        this.brand = brand;
        this.lostDate = lostDate;
        this.lostAddress = lostAddress;
        this.userId = userId;
    }
}
