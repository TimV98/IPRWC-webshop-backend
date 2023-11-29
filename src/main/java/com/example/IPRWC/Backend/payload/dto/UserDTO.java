package com.example.IPRWC.Backend.payload.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class UserDTO {

    private Long id;
    @NotNull(message = "Email can't be null")
    @NotBlank(message = "Email can't be empty")
    private String email;

    @NotBlank(message = "Phonenumber can't be empty")
    private long phoneNumber;

    @NotBlank(message = "First name can't be empty")
    private String firstName;

    private String prefix;

    @NotBlank(message = "Last name can't be empty")
    private String lastName;

    @NotBlank(message = "Street can't be empty")
    private String street;

    @NotBlank(message = "Zipcode can't be empty")
    private String zipCode;

    @NotBlank(message = "Place can't be empty")
    private int houseNumber;

    @NotBlank(message = "Place can't be empty")
    private String place;
}
