package com.tuval.askida.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class JwtToken {
    private String token;
    private Date expiration;
}
