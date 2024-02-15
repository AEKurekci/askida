package com.tuval.askida.service.impl;

import com.tuval.askida.model.Product;
import com.tuval.askida.repository.IProductRepository;
import com.tuval.askida.service.IProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService implements IProductService {

    private final IProductRepository productRepository;

    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product){
        product.setCreateTime(LocalDateTime.now());
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId){
        productRepository.deleteById(productId);
    }

    @Override
    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }
}
