package com.imsisojib.lpd.features.account.models.responses;

public class ResponseGoogleTokenInfo {
    private String iss;
    private String azp;
    private String name;
    private String email;
    private String phone;

    public ResponseGoogleTokenInfo() {
    }

    public ResponseGoogleTokenInfo(String iss, String azp, String name, String email, String phone) {
        this.iss = iss;
        this.azp = azp;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public String getAzp() {
        return azp;
    }

    public void setAzp(String azp) {
        this.azp = azp;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
