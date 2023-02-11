package com.example.IPRWC.Backend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String email;
    private long phoneNumber;
    private String firstName;
    private String prefix;
    private String lastName;
    private String street;
    private String zipCode;
    private int houseNumber;

    private String place;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String email, String password) {
        this.email = email;
        this.password = password;

    }

    public User(String email,String password,  String firstName, String prefix, String lastName, String address,
                String zipCode, int houseNumber, int phoneNumber, String place) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.prefix = prefix;
        this.lastName = lastName;
        this.street = address;
        this.zipCode = zipCode;
        this.houseNumber = houseNumber;
        this.password = password;
        this.place = place;
    }
}
