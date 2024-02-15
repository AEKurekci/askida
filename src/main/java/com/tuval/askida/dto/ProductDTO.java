package com.tuval.askida.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ProductDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime createTime;
    private LocalDateTime expireTime;
    private Integer amount;
    private Long ownerId;

    private OwnerDTO ownerDTO;
    private CategoryDTO categoryDTO;
    private List<ImageInfoDTO> imageDTOs;
    private List<KeywordDTO> keywordDTOs;

}
