package com.tuval.askida.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "message")
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "s", nullable = false)
    private Long s;
    @Column(name = "m", nullable = false)
    private String m;
    @Column(name = "t", nullable = false)
    private LocalDateTime t;
    @Column(name = "message_info_id", nullable = false)
    private Long messageInfoId;

}
