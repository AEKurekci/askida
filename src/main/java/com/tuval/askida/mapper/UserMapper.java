package com.tuval.askida.mapper;

import com.tuval.askida.dto.OwnerDTO;
import com.tuval.askida.model.Owner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public OwnerDTO toDTO(Owner owner){
        OwnerDTO dto = new OwnerDTO();
        if(Objects.nonNull(owner)){
            dto = modelMapper.map(owner, OwnerDTO.class);
        }
        return dto;
    }

    public Owner toModel(OwnerDTO ownerDTO){
        Owner model = new Owner();
        if(Objects.nonNull(ownerDTO)){
            model = modelMapper.map(ownerDTO, Owner.class);
        }
        return model;
    }

    public List<OwnerDTO> toDTOList(List<Owner> owners){
        return owners.stream().map(this::toDTO).toList();
    }
}
