package com.imsisojib.lpd.controllers;

import com.imsisojib.lpd.models.entities.Address;
import com.imsisojib.lpd.models.entities.Diary;
import com.imsisojib.lpd.models.entities.User;
import com.imsisojib.lpd.models.requests.RequestDiaryBody;
import com.imsisojib.lpd.models.responses.Response;
import com.imsisojib.lpd.repositories.AddressRepository;
import com.imsisojib.lpd.repositories.DiaryRepository;
import com.imsisojib.lpd.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.*;
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

    @PostMapping("/create")
    public ResponseEntity<?> createDiary(@RequestBody RequestDiaryBody diaryBody) {
        Optional<User> user = userRepository.findById(diaryBody.getUserId());
        if(user.isEmpty()){
            return ResponseEntity.badRequest().body(
                    new Response(
                            "Invalid user ID found!",
                            null
                    )
            );
        }
        var result = diaryRepository.save(new Diary(
                diaryBody.getEmi(),
                diaryBody.getDeviceName(),
                diaryBody.getModelName(),
                diaryBody.getBrand(),
                diaryBody.getLostDate(),
                diaryBody.getLostAddress(),
                user.get()

        ));
        return ResponseEntity.ok(
                new Response<Diary>(
                        "Successful!",
                        result
                )
        );
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAllDiary() {
        List<Diary> data;
        try{
             data = diaryRepository.findAll();
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(
                new Response(
                        "Successful!",
                        data
                )
        );
    }

}
