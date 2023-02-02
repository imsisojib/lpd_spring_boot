package com.imsisojib.lpd.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String district;
    private String division;
    private String upazilla;
    private String thana;
    private String additional;
}
