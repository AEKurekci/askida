package com.tuval.askida.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Builder
public class JwtResponse {
    private String accessToken;
    private String refreshToken;
    private Date expiration;
    private Long userId;
    private String email;
}
