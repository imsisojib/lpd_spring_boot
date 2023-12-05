package com.imsisojib.lpd.features.lost_diary.controllers;

import com.imsisojib.lpd.features.search.enums.EnumSearchStatus;
import com.imsisojib.lpd.features.lost_diary.models.entities.Diary;
import com.imsisojib.lpd.features.search.models.entities.SearchLogs;
import com.imsisojib.lpd.features.account.models.entities.User;
import com.imsisojib.lpd.core.models.Response;
import com.imsisojib.lpd.features.account.repositories.AddressRepository;
import com.imsisojib.lpd.features.lost_diary.repositories.DiaryRepository;
import com.imsisojib.lpd.features.search.repositories.SearchLogsRepository;
import com.imsisojib.lpd.features.account.repositories.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/diary")
public class DiaryController {
    @Autowired
    RepositoryUser repositoryUser;

    @Autowired
    DiaryRepository diaryRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    SearchLogsRepository searchLogsRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createDiary(@RequestBody Diary.RequestDiaryBody diaryBody) {
        Optional<User> user = repositoryUser.findById(diaryBody.getUserId());
        if (user.isEmpty()) {
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
        try {
            data = diaryRepository.findAll();
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(
                new Response(
                        "Successful!",
                        data
                )
        );
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchDiaryByEmi(@RequestParam final String emi, @RequestParam final String userId, @RequestParam final String searchByPhoneEmi) {

        Optional<Diary> data = diaryRepository.findById(emi);
        Optional<User> userData = repositoryUser.findById(userId);

        if (data.isEmpty()) {
            SearchLogs searchLogs = new SearchLogs();
            searchLogs.setSearchedEmi(emi);
            userData.ifPresent(searchLogs::setSearchByUserId);
            searchLogs.setSearchByPhoneEmi(searchByPhoneEmi);
            searchLogs.setStatus(EnumSearchStatus.NOT_FOUND.getValue());
            searchLogsRepository.save(searchLogs);

            return ResponseEntity.status(404).body(new Response<>(
                    "No diary found by this emi!",
                    null
            ));

        } else {
            SearchLogs searchLogs = new SearchLogs();
            searchLogs.setSearchedEmi(emi);
            userData.ifPresent(searchLogs::setSearchByUserId);
            searchLogs.setSearchByPhoneEmi(searchByPhoneEmi);
            searchLogs.setStatus(EnumSearchStatus.FOUND.getValue());
            searchLogsRepository.save(searchLogs);

            return ResponseEntity.status(200).body(new Response<>(
                    "A diary is found!",
                    data.get()
            ));

        }
    }

}
