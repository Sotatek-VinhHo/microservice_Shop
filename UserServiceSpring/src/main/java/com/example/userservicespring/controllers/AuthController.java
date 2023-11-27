package com.example.userservicespring.controllers;

import com.example.userservicespring.dtos.SigninRequestDto;
import com.example.userservicespring.dtos.TokenSinginResponseDTO;
import com.example.userservicespring.dtos.RegisterRequestDto;
import com.example.userservicespring.services.AuthenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenService authenService;
    @PostMapping("/register")
    public void register(@RequestBody RegisterRequestDto registerRequestDto
    ){
        authenService.register(registerRequestDto);
    }
    @PostMapping("/signin")
    public ResponseEntity<TokenSinginResponseDTO> authenticate(
            @RequestBody SigninRequestDto signinRequestDto
    ){
        return ResponseEntity.ok(authenService.signin(signinRequestDto));
    }
}
