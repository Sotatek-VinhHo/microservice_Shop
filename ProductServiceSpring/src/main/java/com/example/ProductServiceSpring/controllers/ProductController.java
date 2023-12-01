package com.example.ProductServiceSpring.controllers;

import com.example.ProductServiceSpring.dtos.CreateProductDto;
import com.example.ProductServiceSpring.dtos.ProductDto;
import com.example.ProductServiceSpring.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//        POST /product
//        GET /product
//        GET /product/{id}
//        PATCH /product/{id}
//        DELETE /product/{id}
@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("product")
    @ResponseStatus(HttpStatus.CREATED)
    public void postProduct(@RequestBody CreateProductDto createProductDto){
        productService.createProduct(createProductDto);
    }
    @GetMapping("product/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(productService.getProduct(id));
    }
    @DeleteMapping("product/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }
    @PatchMapping("product/{id}")
    public void updateProduct(@PathVariable("id") Long id, ProductDto productDto) {
        productService.updateProduct(id, productDto);
    }
    @GetMapping("allproduct")
    public ResponseEntity<List<ProductDto>> getAllProduct(){
        return ResponseEntity.ok().body(productService.getAllProduct());
    }
}
