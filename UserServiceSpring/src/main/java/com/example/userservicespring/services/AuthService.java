package com.example.userservicespring.services;

import com.example.userservicespring.dtos.*;
import com.example.userservicespring.entities.UserEntity;
import com.example.userservicespring.repositories.UserRepository;
import com.example.userservicespring.exceptions.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor

public class AuthService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;
    private final RestTemplate restTemplate;
    private final String AUTH_SERVICE_API = "http://localhost:9000/api/auth";


    private TokenSigninResponseDTO generateToken(TokenSigninRequestDTO tokenSigninRequestDTO) {
        HttpEntity<TokenSigninRequestDTO> request =
                new HttpEntity<>(tokenSigninRequestDTO);
        ResponseEntity<TokenSigninResponseDTO> responseEntity = restTemplate.postForEntity(AUTH_SERVICE_API + "/generate", request, TokenSigninResponseDTO.class);
        return responseEntity.getBody();
    }
    public SigninVerifyDTO validateToken(String authorizationHeader) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<SigninVerifyDTO> responseEntity = restTemplate.postForEntity(AUTH_SERVICE_API + "/verify", requestEntity, SigninVerifyDTO.class);
        log.info("TokenSinginResponse : {}", responseEntity.getBody());
        return responseEntity.getBody();
    }


    public void register(RegisterRequestDto registerRequestDto){
        userRepository.findByEmail(registerRequestDto.getEmail()).ifPresent(user -> {
            throw new UserExistException();
        });
        UserEntity userEntity = userConverter.convertToEntity(registerRequestDto);
        userEntity.setBalance(0.0);
        userRepository.save(userEntity);
    }

    public TokenSigninResponseDTO signin(SigninRequestDto signinRequestDto) {
        UserEntity foundUserEntity = userRepository.findByEmail(signinRequestDto.email()).orElseThrow(UserNotFoundException::new);
        if (!passwordEncoder.matches(signinRequestDto.password(), foundUserEntity.getPassword())) {
            throw new IncorrectPasswordException();
        }
        TokenSigninRequestDTO tokenSigninRequestDTO = TokenSigninRequestDTO.builder().email(foundUserEntity.getEmail()).password(foundUserEntity.getPassword()).build();
        return generateToken(tokenSigninRequestDTO);
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
