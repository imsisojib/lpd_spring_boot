package com.imsisojib.lpd.controllers;

import com.imsisojib.lpd.models.entities.Address;
import com.imsisojib.lpd.models.entities.Diary;
import com.imsisojib.lpd.models.entities.User;
import com.imsisojib.lpd.repositories.AddressRepository;
import com.imsisojib.lpd.repositories.DiaryRepository;
import com.imsisojib.lpd.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Random;
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
        Random random = new Random();
        /*for(int i=0; i<2; i++){
            long min = 111111111111111L;
            long max = 999999999999999L;
            long newNumber = (long) (Math.random() * (max - min)) + min;

            diaryRepository.save(new Diary(
                    ""+newNumber,
                    "SM-A20",
                    "A20",
                    "Samsung",
                    new Timestamp(new Date().getTime()),
                    new Address(
                            "Rajshahi",
                            "Bogura",
                            "Sherpur",
                            "Sherpur Upazila",
                            "Khandokar Tola"
                    ),
                    new User(
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
                    )
            ));

            *//*userRepository.save(new User(
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
            *//*

        }*/

        return ResponseEntity.ok(diaryRepository.findAll());
    }

}
