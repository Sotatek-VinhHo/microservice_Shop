package com.example.ProductServiceSpring.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "products")
public class ProductEntity {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String description;

    private String image;

//    private Long userId;

    //make column default greater than 0
    @Column(columnDefinition = "int8 default 0 check(amount >= 0)")
    private Long amount;
}