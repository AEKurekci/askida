package com.tuval.askida.service;

import com.tuval.askida.dto.RefreshTokenDTO;
import com.tuval.askida.request.RefreshTokenRequest;
import com.tuval.askida.response.JwtResponse;

public interface IRefreshTokenService {
    RefreshTokenDTO createRefreshToken(String email);

    RefreshTokenDTO updateRefreshToken(RefreshTokenDTO refreshTokenDTO);

    JwtResponse refreshTheToken(RefreshTokenRequest request);

    RefreshTokenDTO verifyExpiration(RefreshTokenDTO tokenDTO);
}
