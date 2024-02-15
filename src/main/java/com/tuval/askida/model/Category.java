package com.tuval.askida.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "category")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "text", nullable = false, length = 100)
    private String name;
    @Column(name = "value", nullable = false)
    private Integer value;
}
