package com.example.ProductServiceSpring.services;

import com.example.ProductServiceSpring.dtos.CreateProductDto;
import com.example.ProductServiceSpring.dtos.ProductConverter;
import com.example.ProductServiceSpring.dtos.ProductDto;
import com.example.ProductServiceSpring.dtos.UpdateProductAmountDto;
import com.example.ProductServiceSpring.entity.ProductEntity;
import com.example.ProductServiceSpring.repositories.ProductRepository;
import com.example.ProductServiceSpring.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public ProductService(ProductRepository productRepository, ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
    }
    public void createProduct(CreateProductDto createProductDto) {
        // Chuyển đổi từ CreateProductDto sang ProductEntity
        ProductEntity productEntity = productConverter.convertToEntity(createProductDto);
        // Lưu sản phẩm vào cơ sở dữ liệu
        productRepository.save(productEntity);
    }
    public List<ProductEntity> findAllProducts() {
        return productRepository.findAll();
    }
    public List<ProductDto> getAllProduct() {
        List<ProductEntity> productList = findAllProducts();
        List<ProductDto> productDtoList = productConverter.convertToDtoList(productList);
        return productDtoList;
    }
    public ProductDto getProduct(Long id) {
        ProductEntity foundProductById = productRepository.findById(id).orElseThrow(NotFoundException::new);
        return productConverter.convertToDto(foundProductById);
    }
    public void deleteProduct(Long id) {
        ProductEntity foundProductById = productRepository.findById(id).orElseThrow(NotFoundException::new);
        productRepository.deleteById(id);
    }

    public void updateProduct(Long id, ProductDto productDto) {
        ProductEntity foundProductById = productRepository.findById(id).orElseThrow(NotFoundException::new);
        if (productDto.getName() != null) {
            foundProductById.setName(productDto.getName());
        }
        if (productDto.getDescription() != null) {
            foundProductById.setDescription(productDto.getDescription());
        }
        productRepository.save(foundProductById);
    }
    public void updateProductAmount(UpdateProductAmountDto updateProductAmountDto) {
        ProductEntity productEntity = productRepository.findById(updateProductAmountDto.id()).orElseThrow(NotFoundException::new);
        productRepository.updateAmount(productEntity.getId(), updateProductAmountDto.amount());
    }
}
