package com.tuval.askida.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RefreshTokenDTO {
    private Long id;
    private String token;
    private LocalDateTime expiryDate;
    private OwnerDTO user;
}
