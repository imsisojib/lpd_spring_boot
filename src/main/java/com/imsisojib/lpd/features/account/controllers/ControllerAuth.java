package com.imsisojib.lpd.features.account.controllers;

import com.imsisojib.lpd.core.security.authentication_providers.CustomSocialAuthenticationProvider;
import com.imsisojib.lpd.core.security.authentication_providers.CustomSocialAuthenticationToken;
import com.imsisojib.lpd.core.security.jwt.JwtUtils;
import com.imsisojib.lpd.features.account.models.entities.User;
import com.imsisojib.lpd.features.account.models.requests.RequestBodyGoogleLogin;
import com.imsisojib.lpd.core.models.Response;
import com.imsisojib.lpd.features.account.models.responses.ResponseAuthenticated;
import com.imsisojib.lpd.features.account.models.responses.ResponseGoogleTokenInfo;
import com.imsisojib.lpd.features.account.services.UserDetailsImpl;
import com.imsisojib.lpd.features.account.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")

public class ControllerAuth {

    @Value("${google.tokenValidatorUrl}")
    private String googleAccessTokenValidator;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomSocialAuthenticationProvider customSocialAuthenticationProvider;

    @Autowired
    JwtUtils jwtUtils;


    @PostMapping("/google")
    public ResponseEntity<?> googleLogin(@Valid @RequestBody RequestBodyGoogleLogin bodyGoogleLogin) {

        // Example validation using RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        String tokenInfoUrl = googleAccessTokenValidator + bodyGoogleLogin.getAccessToken();

        return ResponseEntity.ok(new Response("Sign-in successful", restTemplate.getForObject(tokenInfoUrl,Map.class)));

       /* ResponseGoogleTokenInfo tokenInfo;
        //Map tokenInfo;
        try{
            //tokenInfo = restTemplate.getForObject(tokenInfoUrl, ResponseGoogleTokenInfo.class);
            //tokenInfo = restTemplate.getForObject(tokenInfoUrl,Map.class);
            tokenInfo = new ResponseGoogleTokenInfo();
            tokenInfo.setEmail("imsisojib@gmail.com");
            tokenInfo.setName("Md Sirajul Islam Sojib");
            if(tokenInfo!=null){
                var userExists = userDetailsService.findUserByEmail(tokenInfo.getEmail());
                if(userExists.isEmpty()){
                    //means no user found
                    //just save the user and return as authenticated
                    User newUser = new User();
                    newUser.setName(tokenInfo.getName());
                    newUser.setEmail(tokenInfo.getEmail());
                    userDetailsService.saveUser(newUser);
                }

                //Authentication authentication = authenticationManager.authenticate(new CustomSocialAuthenticationProvider(tokenInfo.getEmail()));
                Authentication authentication = customSocialAuthenticationProvider.authenticate(new CustomSocialAuthenticationToken(tokenInfo.getEmail()));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtUtils.generateJwtToken(authentication);

                UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
                List<String> roles = userDetails.getAuthorities().stream()
                        .map(item -> item.getAuthority())
                        .collect(Collectors.toList());

                return ResponseEntity.ok(new Response("Sign-in successful", new ResponseAuthenticated(
                        jwt,
                        userDetails.getId(),
                        userDetails.getName(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles)));

            }else{
                //can't validate google accessToken, return invalid request
                return ResponseEntity.badRequest().body(
                        new Response(
                                "Unable to validate Google authentication!",
                                null
                        )
                );
            }


        }catch (RestClientException e){
            return ResponseEntity.badRequest().body(
                    new Response(
                            "Invalid request.",
                            e.getMessage()
                    )
            );
        }*/
    }
}
