package com.example.IPRWC.Backend.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class ShoppingCart {
    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "messagesounds_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;

    public int quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    Product product;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    User user;
}
