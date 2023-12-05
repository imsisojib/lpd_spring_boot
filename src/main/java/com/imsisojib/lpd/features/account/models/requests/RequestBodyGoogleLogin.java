package com.imsisojib.lpd.features.account.models.requests;

public class RequestBodyGoogleLogin {
    String accessToken;

    public RequestBodyGoogleLogin() {
    }

    public RequestBodyGoogleLogin(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
