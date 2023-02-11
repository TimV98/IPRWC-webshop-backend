package com.example.IPRWC.Backend.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Product {

    @Id
    @SequenceGenerator(name="pk_sequence",sequenceName="messagesounds_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;
    public int price;
    public String description;
    public String product_rating;
    @OneToOne
    public Image image;
    public String product_name;

}


