package com.imsisojib.lpd.features.lost_diary.controllers;

import com.imsisojib.lpd.features.account.enums.EAddressType;
import com.imsisojib.lpd.features.account.services.UserDetailsImpl;
import com.imsisojib.lpd.features.geocodes.models.entities.Address;
import com.imsisojib.lpd.features.geocodes.repositories.RepositoryDistrict;
import com.imsisojib.lpd.features.geocodes.repositories.RepositoryDivision;
import com.imsisojib.lpd.features.geocodes.repositories.RepositoryUpazila;
import com.imsisojib.lpd.features.lost_diary.models.requests.RequestDiaryBody;
import com.imsisojib.lpd.features.search.enums.ESearchStatus;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    RepositoryDivision repositoryDivision;
    @Autowired
    RepositoryDistrict repositoryDistrict;
    @Autowired
    RepositoryUpazila repositoryUpazila;

    @Autowired
    SearchLogsRepository searchLogsRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createDiary(@RequestBody RequestDiaryBody diaryBody) {
        Long userId;
        try{
            // Get the Authentication object from the security context
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            userId = userDetails.getId();
        }catch (Exception e){
            return ResponseEntity.status(404).body(
                    new Response<Diary>(
                            "Invalid user ID found!",
                            null
                    )
            );
        }




        Optional<User> user = repositoryUser.findUserById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    new Response<Diary>(
                            "Invalid user ID found!",
                            null
                    )
            );
        }

        //check Address information
        Address address = new Address();
        try{
            address.setDivisionId(diaryBody.getDivisionId());
            address.setDistrictId(diaryBody.getDistrictId());
            address.setUpazilaId(diaryBody.getUpazilaId());
        }catch (Exception e){
            //need to specify proper validation if division, district, upazila is mandatory
        }
        address.setDetails(diaryBody.getLostAddressDetails());
        address.setAddressType(EAddressType.ADDRESS_DIARY.name());

        var result = diaryRepository.save(new Diary(
                diaryBody.getEmi(),
                diaryBody.getDeviceName(),
                diaryBody.getModelName(),
                diaryBody.getBrand(),
                diaryBody.getLostDate(),
                address,
                user.get()

        ));
        return ResponseEntity.ok(
                new Response<Diary>(
                        "Your lost diary is saved successfully!",
                        result
                )
        );
    }

    @GetMapping("/findAll")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
    public ResponseEntity<?> searchDiaryByEmi(@RequestParam final String emi, @RequestParam final String searchByPhoneEmi) {

        Long userId;
        try{
            // Get the Authentication object from the security context
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            userId = userDetails.getId();
        }catch (Exception e){
            return ResponseEntity.status(404).body(
                    new Response<Diary>(
                            "You are not allowed to perform this action. Please login first!",
                            null
                    )
            );
        }

        Optional<Diary> data = diaryRepository.findDiaryByEmi(emi);
        Optional<User> userData = repositoryUser.findUserById(userId);

        if (data.isEmpty()) {
            SearchLogs searchLogs = new SearchLogs();
            searchLogs.setSearchedEmi(emi);
            userData.ifPresent(searchLogs::setSearchByUserId);
            searchLogs.setSearchByPhoneEmi(searchByPhoneEmi);
            searchLogs.setStatus(ESearchStatus.NOT_FOUND.getValue());
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
            searchLogs.setStatus(ESearchStatus.FOUND.getValue());
            searchLogsRepository.save(searchLogs);

            return ResponseEntity.status(200).body(new Response<>(
                    "A diary is found!",
                    data.get()
            ));

        }
    }

}
