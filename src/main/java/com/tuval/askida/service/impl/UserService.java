package com.tuval.askida.service.impl;

import com.tuval.askida.dto.OwnerDTO;
import com.tuval.askida.mapper.UserMapper;
import com.tuval.askida.model.Owner;
import com.tuval.askida.repository.IUserRepository;
import com.tuval.askida.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public OwnerDTO saveUser(OwnerDTO userDTO){
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDTO.setSignDate(LocalDateTime.now());
        return userMapper.toDTO(userRepository.save(userMapper.toModel(userDTO)));
    }

    @Override
    public Optional<Owner> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
