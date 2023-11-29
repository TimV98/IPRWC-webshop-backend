package com.example.IPRWC.Backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@With
public class Product {

    @Id
    @SequenceGenerator(name = "product_sequence", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;
    @NotNull
    public int price;
    public String description;
    @NotNull
    public String genre;
    @NotNull
    @Max(value = 18, message = "Max age restriction can't be higher than 18")
    public int age_rating;
    @NotNull
    public String product_name;
    @OneToOne(cascade = CascadeType.ALL)
    public Photo image;

}


