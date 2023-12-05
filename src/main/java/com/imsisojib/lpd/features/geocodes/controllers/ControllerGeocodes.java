package com.imsisojib.lpd.features.geocodes.controllers;

import com.imsisojib.lpd.core.models.Response;
import com.imsisojib.lpd.features.account.models.entities.User;
import com.imsisojib.lpd.features.geocodes.services.ServiceGeocodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/geocodes")
public class ControllerGeocodes {
    @Autowired
    ServiceGeocodes serviceGeocodes;

    @GetMapping("/divisions")
    public ResponseEntity<?> findAllDivisions() {

        return ResponseEntity.ok(
                new Response(
                        "Successful!",
                        serviceGeocodes.findAllDivisions()
                )
        );
    }

    @GetMapping("/districts")
    public ResponseEntity<?> findAllDistricts() {

        return ResponseEntity.ok(
                new Response(
                        "Successful!",
                        serviceGeocodes.findAllDistricts()
                )
        );
    }

    @GetMapping("/upazilas")
    public ResponseEntity<?> findAllUpazilas(@RequestParam("districtId") Long districtId) {

        if(districtId==null){
            return ResponseEntity.ok(
                    new Response(
                            "Successful!",
                            serviceGeocodes.findAllUpazilas()
                    )
            );
        }

        return ResponseEntity.ok(
                new Response(
                        "Successful!",
                        serviceGeocodes.findUpazilasByDistrictId(districtId)
                )
        );
    }
}