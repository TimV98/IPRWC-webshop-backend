package com.example.IPRWC.Backend.payload.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    public long id;
    @NotEmpty
    @Size(min = 2, message = "Product should have at least 2 characters")
    public String product_name;
    @NotEmpty(message = "Price can't be empty")
    public int price;
    public String description;
    @NotEmpty
    @Max(value = 18, message = "Max age rating can't be higher than 18")
    public int age_rating;
    @NotEmpty(message = "Genre can't be empty")
    public String genre;

    public String image;

}
