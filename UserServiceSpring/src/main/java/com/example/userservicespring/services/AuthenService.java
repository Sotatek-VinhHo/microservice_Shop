package com.example.userservicespring.services;

import com.example.userservicespring.dtos.SigninRequestDto;
import com.example.userservicespring.dtos.TokenSinginResponseDTO;
import com.example.userservicespring.dtos.RegisterRequestDto;
import com.example.userservicespring.entities.UserEntity;
import com.example.userservicespring.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthenService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public void register(RegisterRequestDto registerRequestDto){
        var user = UserEntity.builder()
                .email(registerRequestDto.getEmail())
                .address(registerRequestDto.getAddress())
                .phone(registerRequestDto.getPhone())
                .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                .role(registerRequestDto.getRole())
                .build();
        userRepository.save(user);
//        var jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
    }
    public TokenSinginResponseDTO signin(SigninRequestDto signinRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signinRequestDto.getEmail(),
                        signinRequestDto.getPassword()
                )
        );
        //neu nguoi dung dung thi tim nguoi dung qua email
        var user = userRepository.findByEmail(signinRequestDto.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return TokenSinginResponseDTO.builder()
                .token(jwtToken)
                .build();
    }
}
