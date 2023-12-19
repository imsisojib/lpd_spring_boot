package com.imsisojib.lpd.features.geocodes.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @JoinColumn(name = "division_id")
    private Long divisionId;

    @JoinColumn(name = "district_id")
    private Long districtId;

    @JoinColumn(name = "upazila_id")
    private Long upazilaId;

    @Column(name = "address_type")
    @NotNull
    private String addressType; //use EAddressType name

    private String details;

}
