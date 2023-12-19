package com.imsisojib.lpd.features.lost_diary.models.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.imsisojib.lpd.features.geocodes.models.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestDiaryBody {

    private String emi;
    private String deviceName;
    private String modelName;
    private String brand;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lostDate;
    private Long divisionId;
    private Long districtId;
    private Long upazilaId;
    private String lostAddressDetails;

}
