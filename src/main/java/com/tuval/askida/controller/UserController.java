package com.tuval.askida.controller;

import com.tuval.askida.constant.ApiEndpoints;
import com.tuval.askida.dto.OwnerDTO;
import com.tuval.askida.request.GetUserRequest;
import com.tuval.askida.service.IUserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ApiEndpoints.API_USER_URL)
@RequiredArgsConstructor
@Slf4j
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {
    private final IUserService userService;

    @PostMapping("getUser")
    public ResponseEntity<OwnerDTO> getUser(@RequestBody GetUserRequest request){
        return new ResponseEntity<>(userService.getUser(request), HttpStatus.OK);
    }
}
