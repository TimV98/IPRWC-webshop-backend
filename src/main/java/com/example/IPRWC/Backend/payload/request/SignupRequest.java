package com.example.IPRWC.Backend.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SignupRequest {

    private String email;

    private String firstName;

    private String prefix;

    private String lastName;

    private Integer phoneNumber;

    private Integer houseNumber;

    private String place;

    private String street;

    private String zipCode;

    private Set<String> roles;

    private String password;
}
