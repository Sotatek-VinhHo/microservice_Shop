package com.example.userservicespring.controllers;


import com.example.userservicespring.dtos.UdpateUserProfileRequestDTO;
import com.example.userservicespring.dtos.UpdateUserBalanceRequestDTO;
import com.example.userservicespring.dtos.UserConverter;
import com.example.userservicespring.dtos.UserDTO;
import com.example.userservicespring.entities.UserEntity;
import com.example.userservicespring.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getUserProfile() {
        return ResponseEntity.ok().body(userService.getUserProfile());
    }
    @PatchMapping("/profile")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserProfile(@RequestBody UdpateUserProfileRequestDTO userDTO) {
        userService.updateUserProfile(userDTO);
    }
    @PatchMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserBalance(@PathVariable("id") Long id, @RequestBody UpdateUserBalanceRequestDTO balanceDTO) {
        userService.updateUserBalance(id, balanceDTO);
    }
    @GetMapping("/allprofile")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllProfile());
    }
    @GetMapping("/helloMember")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String helloMember(){
        return "hello MEMBER";
    }
    @GetMapping("/helloAdmin")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String helloAdmin(){
        return "hello ADMIN";
    }
}

