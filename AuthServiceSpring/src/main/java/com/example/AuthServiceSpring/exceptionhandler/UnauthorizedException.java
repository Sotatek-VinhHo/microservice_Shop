package com.example.AuthServiceSpring.exceptionhandler;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String message) {
        super(message);
    }
}
