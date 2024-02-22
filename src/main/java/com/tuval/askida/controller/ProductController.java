package com.tuval.askida.controller;

import com.tuval.askida.constant.ApiEndpoints;
import com.tuval.askida.dto.ProductDTO;
import com.tuval.askida.service.IProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = ApiEndpoints.API_PRODUCT_URL)//pre-path
@RequiredArgsConstructor
@Slf4j
@SecurityRequirement(name = "Bearer Authentication")
public class ProductController {

    private final IProductService productService;

    @PostMapping ("saveProduct")// -> api/product
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.saveProduct(productDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("{productId}") // -> api/product/productId
    public ResponseEntity<Long> deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>(productId, HttpStatus.OK);
    }

    @GetMapping("findAll")// -> /api/product
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        log.info("get products");
        return ResponseEntity.ok(productService.findAllProducts());
    }
}
