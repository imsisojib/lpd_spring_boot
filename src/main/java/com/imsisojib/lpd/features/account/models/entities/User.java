package com.imsisojib.lpd.features.account.models.entities;

import com.imsisojib.lpd.features.geocodes.models.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id", "phone_number", "email"})  //declaring that id, phoneNumber, email will be unique
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String email;

    @Column(name = "last_logged_in_date")
    private LocalDateTime lastLoggedInDate;
    @Column(name = "joining_date")
    private LocalDateTime joiningDate;
    @Column(name = "auth_type")
    private String authType;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(  name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_address_id", referencedColumnName = "id")
    private Address address;

}
