package com.tuval.askida.controller;

import com.tuval.askida.constant.ApiEndpoints;
import com.tuval.askida.dto.OwnerDTO;
import com.tuval.askida.dto.RefreshTokenDTO;
import com.tuval.askida.request.RefreshTokenRequest;
import com.tuval.askida.request.SignInRequest;
import com.tuval.askida.response.JwtResponse;
import com.tuval.askida.service.IAuthenticationService;
import com.tuval.askida.service.IRefreshTokenService;
import com.tuval.askida.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ApiEndpoints.API_AUTHENTICATION_URL)
@RequiredArgsConstructor
public class AuthenticationController {
    private final IAuthenticationService authenticationService;
    private final IUserService userService;
    private final IRefreshTokenService refreshTokenService;

    @PostMapping("sign-up")
    public ResponseEntity<OwnerDTO> signUp(@RequestBody OwnerDTO request){
        if(userService.findByEmail(request.getEmail()).isPresent()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.saveUser(request), HttpStatus.CREATED);
    }

    @PostMapping("sign-in")
    public ResponseEntity<JwtResponse> signIn(@RequestBody SignInRequest request){
        String accessToken = authenticationService.signInAndReturnJWT(request);
        RefreshTokenDTO refreshToken = refreshTokenService.createRefreshToken(request.getEmail());
        return new ResponseEntity<>(JwtResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .build(), HttpStatus.OK);
    }

    @PostMapping("refresh-token")
    public ResponseEntity<JwtResponse> refreshToken(@RequestBody RefreshTokenRequest request){
        return new ResponseEntity<>(refreshTokenService.refreshTheToken(request), HttpStatus.OK);
    }
}
