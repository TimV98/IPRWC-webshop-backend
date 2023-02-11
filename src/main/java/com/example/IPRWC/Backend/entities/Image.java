package com.example.IPRWC.Backend.entities;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Data
@ToString
@SequenceGenerator( sequenceName = "IMAGE_SEQUENCE", name = "imgSeq" )
@Entity
public class Image {

    @Id
    @GeneratedValue(generator = "imgSeq")
    private int id;
    private String type;
    private String name;
    @Lob
    private byte[] picture;


}
