package com.tuval.askida.service;

import com.tuval.askida.request.SignInRequest;

public interface IAuthenticationService {
    String signInAndReturnJWT(SignInRequest signInRequest);
}
