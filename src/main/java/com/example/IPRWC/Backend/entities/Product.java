package com.example.IPRWC.Backend.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
//@Table(name = "products")
public class Product {

    @Id
    @SequenceGenerator(name="pk_sequence",sequenceName="messagesounds_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
//    @Column(name = "price")
    public int price;
//    @Column(name = "description")
    public String description;
//    @Column(name = "product_size")
    public String product_size;
//    @Column(name = "image_url")
    public String image_url;
//    @Column(name = "product_name")
    public String product_name;

}


