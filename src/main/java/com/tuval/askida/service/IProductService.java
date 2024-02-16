package com.tuval.askida.service;


import com.tuval.askida.dto.ProductDTO;

import java.util.List;

public interface IProductService {
    ProductDTO saveProduct(ProductDTO productDTO);

    void deleteProduct(Long productId);

    List<ProductDTO> findAllProducts();
}
