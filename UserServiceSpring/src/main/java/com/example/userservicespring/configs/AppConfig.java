package com.example.userservicespring.configs;

import com.example.userservicespring.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    private final UserRepository repository;
    @Bean
    public ModelMapper modelMapper() {
            return new ModelMapper();
        }
    @Bean
    public RestTemplate restTemplate(){return new RestTemplate();}
    @Bean
    public UserDetailsService userDetailsService(){
        return usernameByEmail -> repository.findByEmail(usernameByEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }
