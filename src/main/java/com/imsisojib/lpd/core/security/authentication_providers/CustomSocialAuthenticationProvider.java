package com.imsisojib.lpd.core.security.authentication_providers;

import com.imsisojib.lpd.core.security.jwt.AuthTokenFilter;
import com.imsisojib.lpd.features.account.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomSocialAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Implement your social authentication logic here
        // Extract user details, perform authentication, etc.

        // Example:
         if (authentication instanceof CustomSocialAuthenticationToken) {
             CustomSocialAuthenticationToken socialToken = (CustomSocialAuthenticationToken) authentication;
             String email = socialToken.getEmail();
             // Perform authentication using the social provider (Facebook, Google, etc.)
             // Set authenticated user details in a new Authentication object
             UserDetails userDetails = userDetailsService.findUserDetailsByEmail(email);
             return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
         }

        return null; // Return null if authentication fails
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomSocialAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

