package com.tuval.askida.controller;

import com.tuval.askida.constant.ApiEndpoints;
import com.tuval.askida.dto.OwnerDTO;
import com.tuval.askida.request.SignInRequest;
import com.tuval.askida.service.IAuthenticationService;
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

    @PostMapping("sign-up")
    public ResponseEntity<OwnerDTO> signUp(@RequestBody OwnerDTO request){
        if(userService.findByEmail(request.getEmail()).isPresent()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.saveUser(request), HttpStatus.CREATED);
    }

    @PostMapping("sign-in")
    public ResponseEntity<String> signIn(@RequestBody SignInRequest request){
        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(request), HttpStatus.OK);
    }
}
