package com.example.ProductServiceSpring.dtos;

import com.example.ProductServiceSpring.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductConverter {
    private final ModelMapper modelMapper;

    public ProductDto convertToDto (ProductEntity productEntity){
        return modelMapper.map(productEntity, ProductDto.class);
    }
    public ProductEntity convertToEntity(CreateProductDto createProductDto) {
        return modelMapper.map(createProductDto, ProductEntity.class);
    }

    public List<ProductDto> convertToDtoList(List<ProductEntity> productEntityList) {
        List<ProductDto> productDtoList = new ArrayList<>();

        for (ProductEntity productEntity : productEntityList) {
            ProductDto productDto = convertToDto(productEntity); // Gọi phương thức chuyển đổi cho từng đối tượng UserEntity
            productDtoList.add(productDto); // Thêm đối tượng UserDTO vào danh sách kết quả
        }
        return productDtoList;
    }
}
