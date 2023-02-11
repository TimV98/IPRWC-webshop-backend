package com.example.IPRWC.Backend.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class ShoppingCartItem {

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "messagesounds_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;

    public int price;

    public String product_name;

    public int quantity;
}
