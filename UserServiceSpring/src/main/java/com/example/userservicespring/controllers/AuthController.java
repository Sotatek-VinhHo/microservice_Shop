package com.example.userservicespring.controllers;

import com.example.userservicespring.dtos.SigninRequestDto;
import com.example.userservicespring.dtos.TokenSigninResponseDTO;
import com.example.userservicespring.dtos.RegisterRequestDto;
import com.example.userservicespring.services.AuthenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenService authenService;


    @PostMapping("signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody RegisterRequestDto registerRequestDto
    ){
        authenService.register(registerRequestDto);
    }
    @PostMapping("signin")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<TokenSigninResponseDTO> authenticate(
            @RequestBody SigninRequestDto signinRequestDto
    ){
        authenService.printUserRoles();
        return ResponseEntity.ok().body(authenService.signin(signinRequestDto));
    }
    @GetMapping("role")
    public void roleUser(){
        authenService.printUserRoles();
    }
}
