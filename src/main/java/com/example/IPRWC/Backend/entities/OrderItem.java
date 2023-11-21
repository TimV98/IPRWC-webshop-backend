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
    @SequenceGenerator(name = "pk_sequence", sequenceName = "messagesounds_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;

    public int price;

    public String product_name;

    public int total;

    public int quantity;

}
