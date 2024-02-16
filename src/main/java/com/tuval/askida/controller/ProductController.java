package com.tuval.askida.controller;

import com.tuval.askida.dto.ProductDTO;
import com.tuval.askida.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")//pre-path
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    @PostMapping // -> api/product
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.saveProduct(productDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("{productId}") // -> api/product/productId
    public ResponseEntity<Long> deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>(productId, HttpStatus.OK);
    }

    @GetMapping// -> /api/product
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        return ResponseEntity.ok(productService.findAllProducts());
    }
}
