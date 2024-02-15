package com.tuval.askida.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "image_info")
@Data
public class ImageInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "url", nullable = false)
    private String url;
    @Column(name = "product_id")
    private Long productId;

}
