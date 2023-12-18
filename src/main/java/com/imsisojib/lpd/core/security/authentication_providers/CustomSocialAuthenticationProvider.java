package com.imsisojib.lpd.core.security.authentication_providers;

import com.imsisojib.lpd.features.account.enums.EAuthType;
import com.imsisojib.lpd.features.account.enums.ERole;
import com.imsisojib.lpd.features.account.models.entities.Role;
import com.imsisojib.lpd.features.account.models.entities.User;
import com.imsisojib.lpd.features.account.models.responses.ResponseGoogleTokenInfo;
import com.imsisojib.lpd.features.account.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class CustomSocialAuthenticationProvider implements AuthenticationProvider {
    @Value("${google.tokenValidatorUrl}")
    private String googleAccessTokenValidator;
    @Autowired
    WebClient webClient;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

         //GOOGLE
         if (authentication instanceof GoogleAuthenticationToken) {
             GoogleAuthenticationToken googleToken = (GoogleAuthenticationToken) authentication;

             //test if it is test account
             if(googleToken.getAccessToken().equals("tester@google.com")){
                 //authenticate manually
                 ResponseGoogleTokenInfo googleAccountInfo = new ResponseGoogleTokenInfo();
                 googleAccountInfo.setEmail("tester@google.com");
                 googleAccountInfo.setName("Tester Google");
                 googleAccountInfo.setPhone("");
                 UserDetails userDetails = createGoogleAuthenticatedUser(googleAccountInfo);
                 return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
             }

             ResponseGoogleTokenInfo googleAccountInfo;
             String tokenInfoUrl = googleAccessTokenValidator + googleToken.getAccessToken();

             Mono<ResponseGoogleTokenInfo> result = webClient.get().uri(tokenInfoUrl).exchangeToMono(response -> {
                 if (response.statusCode().equals(HttpStatus.OK)) {
                     return response.bodyToMono(ResponseGoogleTokenInfo.class);
                 } else if (response.statusCode().is4xxClientError()) {
                     return response.bodyToMono(ResponseGoogleTokenInfo.class);
                 } else {
                     return response.createException()
                             .flatMap(Mono::error);
                 }
             });

             googleAccountInfo = result.block();
             if(googleAccountInfo==null || googleAccountInfo.getEmail()==null){
                 return  null;
             }

             UserDetails userDetails = createGoogleAuthenticatedUser(googleAccountInfo);
             return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
         }

        return null; // Return null if authentication fails
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private UserDetails createGoogleAuthenticatedUser(ResponseGoogleTokenInfo googleAccountInfo){
        var userExists = userDetailsService.findUserByEmail(googleAccountInfo.getEmail());
        if(userExists.isEmpty()){
            //means new user
            //just save the user and return as authenticated
            User newUser = new User();
            newUser.setName(googleAccountInfo.getName());
            newUser.setEmail(googleAccountInfo.getEmail());
            newUser.setPhoneNumber(googleAccountInfo.getPhone());
            newUser.setAuthType(EAuthType.google.name());
            newUser.setJoiningDate(LocalDateTime.now());
            newUser.setLastLoggedInDate(LocalDateTime.now());

            //set user role
            Set<Role> roles = new HashSet<>();
            Role userRole = userDetailsService.findRoleByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
            newUser.setRoles(roles);

            userDetailsService.saveUser(newUser);
        }else{
            //means user already exists
            User existingUser = userExists.get();
            existingUser.setLastLoggedInDate(LocalDateTime.now());
            existingUser.setAuthType(EAuthType.google.name());
            userDetailsService.saveUser(existingUser);
        }

        // Perform authentication using the social provider (Facebook, Google, etc.)
        // Set authenticated user details in a new Authentication object
        return userDetailsService.findUserDetailsByEmail(googleAccountInfo.getEmail());
    }
}

