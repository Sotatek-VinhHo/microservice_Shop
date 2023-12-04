package com.example.userservicespring.configs;

import com.example.userservicespring.dtos.SigninVerifyDTO;
import com.example.userservicespring.services.AuthService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final AuthService authService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        SigninVerifyDTO signinVerifyDTO = authService.validateToken(authHeader);
        String email = signinVerifyDTO.email();
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null){
            //Không có người dùng được xác thực, thực hiện các hành động cho người dùng chưa xác thực
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
            );
            authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
            );
                SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(request, response);
    }
}
