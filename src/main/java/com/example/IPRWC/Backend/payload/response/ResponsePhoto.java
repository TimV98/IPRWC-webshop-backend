package com.example.IPRWC.Backend.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponsePhoto {
    private String name;
    private String url;
    private String type;
    private long size;
}
