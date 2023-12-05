package com.imsisojib.lpd.features.account.models.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseAuthenticated {
    private String token;
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private List<String> roles;

}
