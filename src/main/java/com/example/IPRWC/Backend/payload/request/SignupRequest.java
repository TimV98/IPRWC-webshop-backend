package com.example.IPRWC.Backend.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SignupRequest {

    private String email;

    private Set<String> role;

    private String password;
}
