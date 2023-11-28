package com.example.userservicespring.services;

import com.example.userservicespring.dtos.SigninRequestDto;
import com.example.userservicespring.dtos.TokenSigninResponseDTO;
import com.example.userservicespring.dtos.RegisterRequestDto;
import com.example.userservicespring.entities.UserEntity;
import com.example.userservicespring.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

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
    public TokenSigninResponseDTO signin(SigninRequestDto signinRequestDto) {
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
        return TokenSigninResponseDTO.builder()
                .token(jwtToken)
                .build();
    }
    public void printUserRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Lấy danh sách các vai trò của người dùng
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

            // In ra các vai trò
            for (GrantedAuthority authority : authorities) {
                System.out.println("Role: " + authority.getAuthority());
            }
        }
    }
}
