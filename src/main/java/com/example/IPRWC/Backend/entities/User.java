package com.example.IPRWC.Backend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;
    private String firstName;
    private String prefix;
    private String lastName;
    private int phoneNumber;
    private String address;
    private String zipCode;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String role;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


}
