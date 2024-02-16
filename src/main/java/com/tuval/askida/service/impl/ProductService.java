package com.tuval.askida.service.impl;

import com.tuval.askida.dto.ProductDTO;
import com.tuval.askida.mapper.ProductMapper;
import com.tuval.askida.repository.IProductRepository;
import com.tuval.askida.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final IProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDTO saveProduct(ProductDTO product){
        product.setCreateTime(LocalDateTime.now());
        return productMapper.toDTO(productRepository.save(productMapper.toModel(product)));
    }

    @Override
    public void deleteProduct(Long productId){
        productRepository.deleteById(productId);
    }

    @Override
    public List<ProductDTO> findAllProducts(){
        return productMapper.toDTOList(productRepository.findAll());
    }
}
