package com.tuval.askida.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "address")
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "text", nullable = false, length = 100)
    private String name;
    @Column(name = "lat", nullable = false)
    private String lat;
    @Column(name = "lng", nullable = false)
    private String lng;
}
