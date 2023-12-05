package com.imsisojib.lpd.core.security.authentication_providers;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class CustomSocialAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final String email;

    public CustomSocialAuthenticationToken(String email) {
        super(null, null);
        this.email = email;
        setAuthenticated(false);
    }

    public String getEmail() {
        return email;
    }
}
