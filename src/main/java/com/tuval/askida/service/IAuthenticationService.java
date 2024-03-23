package com.tuval.askida.service;

import com.tuval.askida.dto.JwtToken;
import com.tuval.askida.request.SignInRequest;

public interface IAuthenticationService {
    JwtToken signInAndReturnJWT(SignInRequest signInRequest);
}
