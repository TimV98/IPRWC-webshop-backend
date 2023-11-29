package com.example.IPRWC.Backend.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class OrderItem {

    @Id
    @SequenceGenerator(name = "OI_sequence", sequenceName = "OI_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;

    public int price;

    public String product_name;

    public int quantity;

}
