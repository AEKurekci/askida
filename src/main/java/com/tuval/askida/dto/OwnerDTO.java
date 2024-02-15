package com.tuval.askida.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class OwnerDTO {

    private Long id;
    private String name;
    private String imageURL;
    private LocalDateTime signDate;
    private Long followingsId;
    private Long followersId;
    private Long favoritesId;
    private Long addressId;

    private AddressDTO addressDTO;
}
