package com.imsisojib.lpd.features.account.models.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseGoogleTokenInfo {
    private String iss;
    private String azp;
    private String name;
    private String email;
    private String phone;

}
