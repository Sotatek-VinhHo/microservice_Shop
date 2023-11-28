package com.example.userservicespring.controllers;


import com.example.userservicespring.dtos.UdpateUserProfileRequestDTO;
import com.example.userservicespring.dtos.UpdateUserBalanceRequestDTO;
import com.example.userservicespring.dtos.UserConverter;
import com.example.userservicespring.dtos.UserDTO;
import com.example.userservicespring.entities.UserEntity;
import com.example.userservicespring.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")

public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;

    public UserController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @GetMapping("/{id}/profile")
    public ResponseEntity<UserDTO> getUserProfile(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(userConverter.convertToDto(userService.findUserById(id)));
    }
    @GetMapping("/allprofile")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserEntity> userList = userService.findAllUsers();
        List<UserDTO> userDTOList = userConverter.convertToDtoList(userList);
        return ResponseEntity.ok().body(userDTOList);
    }

    @PatchMapping("/{id}/profile")
    public void updateUserProfile(@PathVariable("id") Long id, @RequestBody UdpateUserProfileRequestDTO userDTO) {
        userService.updateUserProfile(id, userDTO.address(), userDTO.phone());
    }
    @PatchMapping("/{id}/balance")
    public void updateUserBalance(@PathVariable("id") Long id, @RequestBody UpdateUserBalanceRequestDTO balanceDTO) {
        userService.updateUserBalance(id, balanceDTO.balance());
    }


    @GetMapping("/helloMember")
    public String helloMember(){
        return "hello MEMBER";
    }
    @GetMapping("/helloAdmin")
    public String helloAdmin(){
        return "hello ADMIN";
    }
}

