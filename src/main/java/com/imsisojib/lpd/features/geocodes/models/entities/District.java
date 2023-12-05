package com.imsisojib.lpd.features.geocodes.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "districts")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "division_id")
    private Long divisionId;

    private String name;
    @Column(name = "bn_name")
    private String bnName;
    private String lat;
    private String lon;
    private String url;

    public District() {
    }

    public District(Long id, Long divisionId, String name, String bnName, String lat, String lon, String url) {
        this.id = id;
        this.divisionId = divisionId;
        this.name = name;
        this.bnName = bnName;
        this.lat = lat;
        this.lon = lon;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(Long divisionId) {
        this.divisionId = divisionId;
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

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
