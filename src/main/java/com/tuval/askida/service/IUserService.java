package com.tuval.askida.service;

import com.tuval.askida.dto.OwnerDTO;
import com.tuval.askida.model.Owner;
import com.tuval.askida.request.GetUserRequest;

import java.util.Optional;

public interface IUserService {
    OwnerDTO saveUser(OwnerDTO userDTO);

    Optional<Owner> findByEmail(String email);

    OwnerDTO getUser(GetUserRequest request);
}
