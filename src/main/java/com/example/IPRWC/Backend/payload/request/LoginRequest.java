package com.example.IPRWC.Backend.payload.request;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@Getter
@Setter

public class LoginRequest {

    @NotNull
    private String email;

    @NotNull
    private String password;
}
