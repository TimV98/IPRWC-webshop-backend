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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;
    public int price;
    public String description;
    public String product_size;
    public String image_url;
    public String product_name;

}


