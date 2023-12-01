package com.example.ProductServiceSpring.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Product not found");
    }
}
