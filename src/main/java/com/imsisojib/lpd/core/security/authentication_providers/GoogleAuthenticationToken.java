package com.imsisojib.lpd.core.security.authentication_providers;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class GoogleAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final String accessToken;

    public GoogleAuthenticationToken(String accessToken) {
        super(null, null);
        this.accessToken = accessToken;
        setAuthenticated(false);
    }

    public String getAccessToken() {
        return accessToken;
    }
}
