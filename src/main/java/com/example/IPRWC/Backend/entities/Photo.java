package com.example.IPRWC.Backend.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
@NoArgsConstructor
@SequenceGenerator(name = "photoSeq", sequenceName = "PHOTO_SEQUENCE")

public class Photo {
    @Id
    @GeneratedValue(generator = "photoSeq")
    private long id;

    private String name;

    private String type;

    @Lob
    @Column(name = "imagedata", length = 1000)
    private byte[] data;

}
