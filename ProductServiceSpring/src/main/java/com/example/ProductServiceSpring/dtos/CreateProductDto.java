package com.example.ProductServiceSpring.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
    public class CreateProductDto {
        private String name;
        private String description;
        private String image;
        private Double amount;
    }
