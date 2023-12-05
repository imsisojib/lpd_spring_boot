package com.imsisojib.lpd.features.geocodes.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "upazilas")
public class Upazila {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "district_id")
    private Long districtId;

    private String name;
    @Column(name = "bn_name")
    private String bnName;
    private String url;

    public Upazila() {
    }

    public Upazila(Long id, Long districtId, String name, String bnName, String url) {
        this.id = id;
        this.districtId = districtId;
        this.name = name;
        this.bnName = bnName;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBnName() {
        return bnName;
    }

    public void setBnName(String bnName) {
        this.bnName = bnName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
