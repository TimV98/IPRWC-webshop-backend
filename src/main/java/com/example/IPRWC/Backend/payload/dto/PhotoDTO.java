package com.example.IPRWC.Backend.payload.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotoDTO {

    @JsonIgnore
    private long id;
    private String name;
    private String image_url;
    private String type;
    @JsonIgnore
    private byte[] data;
}
