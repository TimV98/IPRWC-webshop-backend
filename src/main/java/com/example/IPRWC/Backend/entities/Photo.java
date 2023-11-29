package com.example.IPRWC.Backend.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
@With
@NoArgsConstructor
@SequenceGenerator(name = "photoSeq", sequenceName = "PHOTO_SEQUENCE")

public class Photo {
    @Id
    @GeneratedValue(generator = "photoSeq", strategy = GenerationType.SEQUENCE)
    private int id;

    private String name;

    private String type;

    @Lob
    @Column(name = "imagedata", length = 1000)
    private byte[] data;
}
