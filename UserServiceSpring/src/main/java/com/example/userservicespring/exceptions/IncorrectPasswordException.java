package com.example.userservicespring.exceptions;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException() {
        super("Incorrect password");
    }
}
