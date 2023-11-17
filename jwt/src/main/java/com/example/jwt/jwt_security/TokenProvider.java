package com.example.jwt.jwt_security;

import com.example.jwt.exceptionhandler.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class TokenProvider {
    @Value("Bearer ")
    private String prefix;

    private static final Logger log = LoggerFactory.getLogger(TokenProvider.class);

    public String validateToken(String authToken) {
//        if (authToken == null || !authToken.startsWith(prefix)) {
//            log.info("JWT token is missing or invalid");
//        }
//
//        String tokencheck = authToken.substring(7);
//        try {
//            parserBuilder().setSigningKey(AUTHORITIES_KEY).build().parseClaimsJws(tokencheck).getBody().getSubject();
//
//        } catch (JwtException | IllegalArgumentException e) {
//            log.info("Invalid JWT token.");
//            log.trace("Invalid JWT token trace.", e);
//        }
//
//        return "true";
        if(authToken == null || !authToken.startsWith(prefix)) {
            throw new UnauthorizedException("JWT token is missing or invalid");
        }

        String token = authToken.substring(7);

        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(AUTHORITIES_KEY).build().parseClaimsJws(token).getBody();
            System.out.println(claims);
            String subject = claims.getSubject();
            System.out.println(subject);
            claims.get("username");
            return Jwts.parserBuilder().setSigningKey(AUTHORITIES_KEY).build().parseClaimsJws(token).getBody().getSubject();
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new UnauthorizedException("JWT token is invalid");
        }
    }



    private static final String AUTHORITIES_KEY = "authhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh";
    static final long EXPIRATIONTIME = 36_000_000; // 10 phut 36_000_000
    public String createToken(String username) {
        System.out.println(username);
        Map<String,String> data = new HashMap<String,String>();
        data.put("username",username);
        return Jwts.builder()
                .setSubject(username)
                .setClaims(data)
                .signWith(SignatureAlgorithm.HS256, AUTHORITIES_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .compact();
    }
}