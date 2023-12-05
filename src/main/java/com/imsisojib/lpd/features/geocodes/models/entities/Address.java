package com.imsisojib.lpd.features.geocodes.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String district;
    private String division;
    private String upazila;
    private String thana;
    private String additional;

    public Address() {
    }

    public Address(String division, String district, String upazilla, String thana, String additional) {
        this.district = district;
        this.division = division;
        this.upazila = upazilla;
        this.thana = thana;
        this.additional = additional;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getUpazila() {
        return upazila;
    }

    public void setUpazila(String upazilla) {
        this.upazila = upazilla;
    }

    public String getThana() {
        return thana;
    }

    public void setThana(String thana) {
        this.thana = thana;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }
}
