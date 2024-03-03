package com.tuval.askida.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class JwtResponse {
    private String accessToken;
    private String refreshToken;
}
