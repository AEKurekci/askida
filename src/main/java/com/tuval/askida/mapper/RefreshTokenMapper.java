package com.tuval.askida.mapper;

import com.tuval.askida.dto.RefreshTokenDTO;
import com.tuval.askida.model.RefreshToken;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RefreshTokenMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public RefreshTokenMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public RefreshTokenDTO toDTO(RefreshToken refreshToken){
        RefreshTokenDTO dto = new RefreshTokenDTO();
        if(Objects.nonNull(refreshToken)){
            dto = modelMapper.map(refreshToken, RefreshTokenDTO.class);
        }
        return dto;
    }

    public RefreshToken toModel(RefreshTokenDTO refreshTokenDTO){
        RefreshToken model = new RefreshToken();
        if(Objects.nonNull(refreshTokenDTO)){
            model = modelMapper.map(refreshTokenDTO, RefreshToken.class);
        }
        return model;
    }
}
