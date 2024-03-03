package com.tuval.askida.service.impl;

import com.tuval.askida.request.SignInRequest;
import com.tuval.askida.security.UserPrincipal;
import com.tuval.askida.security.jwt.IJwtProvider;
import com.tuval.askida.service.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final IJwtProvider jwtProvider;

    @Override
    public String signInAndReturnJWT(SignInRequest signInRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword())
        );

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return jwtProvider.generateToken(userPrincipal);
    }
}
