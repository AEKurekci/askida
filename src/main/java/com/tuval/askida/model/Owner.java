package com.tuval.askida.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owner")
@Data
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "surname", nullable = false, length = 100)
    private String surname;
    @Column(name = "image_url")
    private String imageURL;
    @Column(name = "sign_date", nullable = false)
    private LocalDateTime signDate;
    @Column(name = "followings_id")
    private Long followingsId;
    @Column(name = "followers_id")
    private Long followersId;
    @Column(name = "favorites_id")
    private Long favoritesId;
    @Column(name = "phone_no", unique = true, nullable = false, length = 13)
    private String phoneNo;
    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address", referencedColumnName = "id")
    private Address address;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "followings_id")
    private List<Owner> followings = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "followers_id")
    private List<Owner> followers = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "favorites_id")
    private List<Owner> favorites = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private List<MessageInfo> messages = new ArrayList<>();
}
