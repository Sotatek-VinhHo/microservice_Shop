package com.example.userservicespring.configs;

import com.example.userservicespring.entities.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests(
                        req -> req.antMatchers("/auth/**", "/allprofile","/swagger-ui/**", "/docs/**").permitAll()
                                .regexMatchers(HttpMethod.GET, "/\\d+/profile").hasAnyAuthority(String.valueOf(Role.MEMBER),String.valueOf(Role.ADMIN))
                                .regexMatchers(HttpMethod.PATCH, "/\\d+/profile").hasAnyAuthority(String.valueOf(Role.MEMBER),String.valueOf(Role.ADMIN))
                                .regexMatchers(HttpMethod.GET, "/helloMember").hasAuthority(String.valueOf(Role.MEMBER))
                                .regexMatchers(HttpMethod.GET, "/helloAdmin").hasAuthority(String.valueOf(Role.ADMIN))
                                .regexMatchers(HttpMethod.PATCH, "/\\d+/balance").hasAuthority(String.valueOf(Role.ADMIN))
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}

