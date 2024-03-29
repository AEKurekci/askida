package com.tuval.askida.service.impl;

import com.tuval.askida.dto.JwtToken;
import com.tuval.askida.dto.OwnerDTO;
import com.tuval.askida.dto.RefreshTokenDTO;
import com.tuval.askida.mapper.RefreshTokenMapper;
import com.tuval.askida.mapper.UserMapper;
import com.tuval.askida.model.Owner;
import com.tuval.askida.model.RefreshToken;
import com.tuval.askida.repository.IRefreshTokenRepository;
import com.tuval.askida.repository.IUserRepository;
import com.tuval.askida.request.RefreshTokenRequest;
import com.tuval.askida.response.JwtResponse;
import com.tuval.askida.security.jwt.IJwtProvider;
import com.tuval.askida.service.IRefreshTokenService;
import com.tuval.askida.util.TokenValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService implements IRefreshTokenService {
    private final IRefreshTokenRepository refreshTokenRepository;
    private final IUserRepository userRepository;
    private final RefreshTokenMapper refreshTokenMapper;
    private final UserMapper userMapper;
    private final IJwtProvider jwtProvider;

    @Override
    public RefreshTokenDTO createRefreshToken(String email){
        Owner user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Not found user by given email"));
        RefreshToken refreshToken = refreshTokenRepository.findByUserId(user.getId())
                .map(token -> {
                    try {
                        return refreshTokenMapper.toModel(verifyExpiration(refreshTokenMapper.toDTO(token)));
                    } catch (TokenValidationException e) {
                        return RefreshToken.builder()
                                .user(user)
                                .token(UUID.randomUUID().toString())
                                .expiryDate(LocalDateTime.now().plusDays(7))
                                .build();
                    }
                })
                .orElse(RefreshToken.builder()
                        .user(user)
                        .token(UUID.randomUUID().toString())
                        .expiryDate(LocalDateTime.now().plusDays(7))
                        .build());
        return refreshTokenMapper.toDTO(refreshTokenRepository.save(refreshToken));
    }

    @Override
    public RefreshTokenDTO updateRefreshToken(RefreshTokenDTO refreshTokenDTO){
        RefreshTokenDTO tokenDTO = refreshTokenRepository.findByToken(refreshTokenDTO.getToken())
                .map(refreshTokenMapper::toDTO).orElseThrow(() -> new RuntimeException("Not found Refresh Token"));
        tokenDTO.setExpiryDate(LocalDateTime.now().plusDays(7));
        tokenDTO.setToken(UUID.randomUUID().toString());
        return refreshTokenMapper.toDTO(refreshTokenRepository.save(refreshTokenMapper.toModel(tokenDTO)));
    }

    @Override
    public JwtResponse refreshTheAccessToken(RefreshTokenRequest request){
        return refreshTokenRepository
                .findByToken(request.getRefreshToken())
                .map(refreshToken -> refreshTokenMapper.toModel(verifyExpiration(refreshTokenMapper.toDTO(refreshToken))))
                .map(refreshToken -> {
                    OwnerDTO user = userMapper.toDTO(refreshToken.getUser());
                    JwtToken jwtToken = jwtProvider.generateToken(user.getId(), user.getEmail());
                    RefreshTokenDTO updatedRefreshToken = updateRefreshToken(refreshTokenMapper.toDTO(refreshToken));
                    return JwtResponse
                            .builder()
                            .accessToken(jwtToken.getToken())
                            .expiration(jwtToken.getExpiration())
                            .refreshToken(updatedRefreshToken.getToken())
                            .email(user.getEmail())
                            .userId(user.getId())
                            .build();
                })
                .orElseThrow(() -> new RuntimeException("Refresh token not found in DB"));
    }

    @Override
    public RefreshTokenDTO verifyExpiration(RefreshTokenDTO tokenDTO) {
        if(tokenDTO.getExpiryDate().isBefore(LocalDateTime.now())){
            refreshTokenRepository.delete(refreshTokenMapper.toModel(tokenDTO));
            throw new TokenValidationException(tokenDTO.getToken() + "Refresh token is expired. Please make a new login..!");
        }
        return tokenDTO;
    }
}
