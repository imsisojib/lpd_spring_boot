package com.imsisojib.lpd.features.lost_diary.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.imsisojib.lpd.features.account.models.entities.User;
import com.imsisojib.lpd.features.geocodes.models.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "diaries")
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(max = 30)
    private String emi;
    private String deviceName;
    private String modelName;
    private String brand;
    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "lost_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lostDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_lost_address_id")
    private Address lostAddress;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_owner_id")
    private User owner; //means many diaries will have same User

    public Diary(String emi, String deviceName, String modelName, String brand, LocalDateTime lostDate, Address lostAddress, User owner) {
        this.emi = emi;
        this.deviceName = deviceName;
        this.modelName = modelName;
        this.brand = brand;
        this.lostDate = lostDate;
        this.lostAddress = lostAddress;
        this.owner = owner;
        this.createdDate = LocalDateTime.now();
    }
}
