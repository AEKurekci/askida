package com.tuval.askida.mapper;

import com.tuval.askida.dto.ProductDTO;
import com.tuval.askida.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ProductMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public ProductMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public ProductDTO toDTO(Product product){
        ProductDTO dto = new ProductDTO();
        if(Objects.nonNull(product)){
            dto = modelMapper.map(product, ProductDTO.class);
        }
        return dto;
    }

    public Product toModel(ProductDTO productDTO){
        Product model = new Product();
        if(Objects.nonNull(productDTO)){
            model = modelMapper.map(productDTO, Product.class);
        }
        return model;
    }

    public List<ProductDTO> toDTOList(List<Product> products){
        return products.stream().map(this::toDTO).toList();
    }
}
