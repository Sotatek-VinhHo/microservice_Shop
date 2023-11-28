package com.example.jwt.controller;

import com.example.jwt.dtos.responsedto.JwtVerifyResponseDTO;
import com.example.jwt.dtos.responsedto.TokenResponse;
import com.example.jwt.entity.User;
import com.example.jwt.jwt_security.TokenProvider;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
//@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("")
public class AuthController {

    private final TokenProvider tokenProvider;

    public AuthController(TokenProvider tokenProvider) {

        this.tokenProvider = tokenProvider;
    }

    @PostMapping("generate")
    public TokenResponse authorize (@RequestBody User user) {
        String token = tokenProvider.createToken(user.getUsername());
        return new TokenResponse(token);
    }
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