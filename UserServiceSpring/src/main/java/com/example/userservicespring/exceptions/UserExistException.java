package com.example.userservicespring.exceptions;

public class UserExistException extends RuntimeException{
    public UserExistException() {
        super("User already exists");
    }
}
