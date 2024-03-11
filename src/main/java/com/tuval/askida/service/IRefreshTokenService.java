package com.tuval.askida.service;

import com.tuval.askida.dto.RefreshTokenDTO;
import com.tuval.askida.request.RefreshTokenRequest;
import com.tuval.askida.response.JwtResponse;
import com.tuval.askida.util.TokenValidationException;

public interface IRefreshTokenService {
    RefreshTokenDTO createRefreshToken(String email);

    RefreshTokenDTO updateRefreshToken(RefreshTokenDTO refreshTokenDTO);

    JwtResponse refreshTheAccessToken(RefreshTokenRequest request);

    RefreshTokenDTO verifyExpiration(RefreshTokenDTO tokenDTO) throws TokenValidationException;
}
