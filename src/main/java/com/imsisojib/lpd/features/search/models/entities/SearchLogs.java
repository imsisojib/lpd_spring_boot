package com.imsisojib.lpd.features.search.models.entities;

import com.imsisojib.lpd.features.account.models.entities.User;

import javax.persistence.*;

@Entity
@Table(name = "search_logs")
public class SearchLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String searchedEmi;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User searchByUserId;
    String searchByPhoneEmi;
    int status; //Will determine by SearchStatus table

    public SearchLogs() {
    }

    public SearchLogs(long id, String searchedEmi, User searchByUserId, String searchByPhoneEmi, int status) {
        this.id = id;
        this.searchedEmi = searchedEmi;
        this.searchByUserId = searchByUserId;
        this.searchByPhoneEmi = searchByPhoneEmi;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSearchedEmi() {
        return searchedEmi;
    }

    public void setSearchedEmi(String searchedEmi) {
        this.searchedEmi = searchedEmi;
    }

    public User getSearchByUserId() {
        return searchByUserId;
    }

    public void setSearchByUserId(User searchByUserId) {
        this.searchByUserId = searchByUserId;
    }

    public String getSearchByPhoneEmi() {
        return searchByPhoneEmi;
    }

    public void setSearchByPhoneEmi(String searchByPhoneEmi) {
        this.searchByPhoneEmi = searchByPhoneEmi;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
