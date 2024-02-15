package com.tuval.askida.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "message_info")
@Data
public class MessageInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "target_user", nullable = false)
    private Long targetUser;
    @Column(name = "product_id", nullable = false)
    private Long productId;
    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "message_info_id")
    private List<Message> message = new ArrayList<>();

}
