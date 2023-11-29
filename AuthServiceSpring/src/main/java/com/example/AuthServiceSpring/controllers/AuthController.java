package com.example.AuthServiceSpring.controllers;

import com.example.AuthServiceSpring.dtos.JwtVerifyResponseDTO;
import com.example.AuthServiceSpring.dtos.TokenResponse;
import com.example.AuthServiceSpring.entity.User;
import com.example.AuthServiceSpring.jwtsecurity.TokenProvider;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@RestController
public class AuthController {
    private final TokenProvider tokenProvider;

    public AuthController(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }
    @Operation
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Generate JWT successfully")
    })
    @PostMapping("generate")
    public TokenResponse authorize (@RequestBody User user) {
        String token = tokenProvider.createToken(user.getUsername());
        return new TokenResponse(token);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Verify JWT Successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
    })
    @PostMapping("verify")

    public ResponseEntity<JwtVerifyResponseDTO> validateToken(@RequestHeader("Authorization") String token) {
        System.out.println(token);
        String username = tokenProvider.validateToken(token);
        System.out.println(username);
        return ResponseEntity.ok().body(
                new JwtVerifyResponseDTO(username)
        );
    }


    @GetMapping("data")
    public List<String> data(){
        List<String> lists = new ArrayList<>();
        lists.add("abc");
        lists.add("123");
        return lists;
    }
}
