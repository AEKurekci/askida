package com.tuval.askida.controller;

import com.tuval.askida.model.Product;
import com.tuval.askida.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/product")//pre-path
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    @PostMapping // -> api/product
    public ResponseEntity<?> saveProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }

    @DeleteMapping("{productId}") // -> api/product/productId
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping// -> /api/product
    public ResponseEntity<?> getAllProducts(){
        return ResponseEntity.ok(productService.findAllProducts());
    }
}
