package com.imsisojib.lpd.features.geocodes.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "division_id")
    private Long divisionId;
    @Column(name = "district_id")
    private Long districtId;
    @Column(name = "upazila_id")
    private Long upazilaId;

    private String details;

}
