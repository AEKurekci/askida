package com.tuval.askida.security.jwt;

import com.tuval.askida.dto.JwtToken;
import com.tuval.askida.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface IJwtProvider {
    JwtToken generateToken(UserPrincipal authentication);

    JwtToken generateToken(Long id, String email);

    Authentication getAuthentication(HttpServletRequest request);

    boolean isTokenValid(HttpServletRequest request);
}
