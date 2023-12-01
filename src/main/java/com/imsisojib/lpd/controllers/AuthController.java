package com.imsisojib.lpd.controllers;

import com.imsisojib.lpd.models.entities.User;
import com.imsisojib.lpd.models.requests.RequestBodyGoogleLogin;
import com.imsisojib.lpd.models.responses.Response;
import com.imsisojib.lpd.models.responses.ResponseGoogleTokenInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

    @Value("${google.tokenValidatorUrl}")
    private String googleAccessTokenValidator;

    @PostMapping("/google")
    public ResponseEntity<?> googleLogin(@RequestBody RequestBodyGoogleLogin bodyGoogleLogin) {

        // Example validation using RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        String tokenInfoUrl = googleAccessTokenValidator + bodyGoogleLogin.getAccessToken();


        //ResponseGoogleTokenInfo tokenInfo;
        Map tokenInfo;
        try{
            //tokenInfo = restTemplate.getForObject(tokenInfoUrl, ResponseGoogleTokenInfo.class);
            tokenInfo = restTemplate.getForObject(tokenInfoUrl,Map.class);
        }catch (RestClientException e){
            return ResponseEntity.badRequest().body(
                    new Response(
                            "Invalid request.",
                            e.getMessage()
                    )
            );
        }

        return ResponseEntity.ok(
                new Response(
                        "Successful!",
                        tokenInfo
                )
        );
    }
}
