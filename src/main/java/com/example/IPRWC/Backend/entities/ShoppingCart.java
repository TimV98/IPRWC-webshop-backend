package com.example.IPRWC.Backend.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, targetEntity = ShoppingCartItem.class)
    @JoinColumn(name = "shopping_cart_id", referencedColumnName = "id")
    List<ShoppingCartItem> products;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;
}
