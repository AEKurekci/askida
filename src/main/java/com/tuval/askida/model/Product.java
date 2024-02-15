package com.tuval.askida.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", length = 100, nullable = false)
    private String title;
    @Column(name = "description", length = 300, nullable = false)
    private String description;
    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;
    @Column(name = "expire_time", nullable = false)
    private LocalDateTime expireTime;
    @Column(name = "amount", nullable = false)
    private Integer amount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner", referencedColumnName = "id")
    private Owner owner;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category", referencedColumnName = "id")
    private Category category;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private List<ImageInfo> images = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private List<Keyword> keywords = new ArrayList<>();

}
