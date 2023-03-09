package com.imsisojib.lpd.controllers;

import com.imsisojib.lpd.models.entities.Diary;
import com.imsisojib.lpd.models.entities.User;
import com.imsisojib.lpd.models.responses.Response;
import com.imsisojib.lpd.repositories.AddressRepository;
import com.imsisojib.lpd.repositories.DiaryRepository;
import com.imsisojib.lpd.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    DiaryRepository diaryRepository;

    @Autowired
    AddressRepository addressRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody User user) {
        var result = userRepository.save(user);
        return ResponseEntity.ok(
                new Response(
                        "Successful!",
                        result
                )
        );
    }

    @GetMapping("/allUsers")
    public ResponseEntity<?> allUsers() {
        return ResponseEntity.ok(
                new Response(
                        "Successful!",
                        userRepository.findAll()
                )
        );
    }
}
