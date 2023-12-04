package com.example.AuthServiceSpring.controllers;

import com.example.AuthServiceSpring.dtos.JwtGenRequestDto;
import com.example.AuthServiceSpring.dtos.JwtVerifyResponseDTO;
import com.example.AuthServiceSpring.dtos.TokenResponse;
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
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Generate JWT successfully")})
    @PostMapping("generate")

    public ResponseEntity<TokenResponse> genToken(@RequestBody JwtGenRequestDto jwtGenRequestDto){
        String token = tokenProvider.createToken(jwtGenRequestDto.email());
        return ResponseEntity.ok().body(new TokenResponse(token));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Verify JWT Successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
    })
    @PostMapping("verify")
    public ResponseEntity<JwtVerifyResponseDTO> validateToken(@RequestHeader("Authorization") String token) {
        String email = tokenProvider.validateToken(token);
        return ResponseEntity.ok().body(
                new JwtVerifyResponseDTO(email)
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
