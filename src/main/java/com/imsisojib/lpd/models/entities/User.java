package com.imsisojib.lpd.models.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "phoneNumber")
})
public class User {
    @Id
    @Size(min = 11, max = 11)
    private String phoneNumber;
    private String name;
    private String email;

    @OneToOne
    @JoinColumn(  name = "address_id",referencedColumnName = "id")
    private Address address;
}
