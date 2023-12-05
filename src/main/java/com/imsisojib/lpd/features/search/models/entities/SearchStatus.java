package com.imsisojib.lpd.features.search.models.entities;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Entity
@Table(name = "search_status")
public class SearchStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String status;

    public SearchStatus() {
    }

    public SearchStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
