package com.imsisojib.lpd.controllers;

import com.imsisojib.lpd.models.entities.Address;
import com.imsisojib.lpd.models.entities.User;
import com.imsisojib.lpd.repositories.AddressRepository;
import com.imsisojib.lpd.repositories.DiaryRepository;
import com.imsisojib.lpd.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/diary")
public class DiaryController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    DiaryRepository diaryRepository;

    @Autowired
    AddressRepository addressRepository;

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        userRepository.save(new User(
                "01521315259",
                "Sirajul Islam",
                "imsisojib@gmail.com",
                new Address(
                        "Rajshahi",
                        "Bogura",
                        "Sherpur",
                        "Sherpur Upazila",
                        "Khandokar Tola"
                        )
        ));
        return ResponseEntity.ok("Test is successful.");
    }

}
