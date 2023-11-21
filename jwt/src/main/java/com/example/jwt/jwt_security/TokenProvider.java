
package com.example.jwt.jwt_security;

import com.example.jwt.exceptionhandler.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class TokenProvider {
    @Value("Bearer ")
    private String prefix;

    private static final Logger log = LoggerFactory.getLogger(TokenProvider.class);

    private Key key;

    public String validateToken(String authToken) {

        if(authToken == null || !authToken.startsWith(prefix)) {
            throw new UnauthorizedException("JWT token is missing or invalid");
        }

        String token = authToken.split(" ")[1].trim();

        try {
            System.out.println(Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody());
            return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new UnauthorizedException("JWT token is invalid");
        }
    }

    @PostConstruct
    public void init() {
        byte[] keyBytes = new byte[0];
        String secret = "this is secret this is secret this is secret this is secret this is secret this is secret this is secret this is secret";
        keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    static final long EXPIRATIONTIME = 36_000_000; // 10 phut 36_000_000
    public String createToken(String username) {
        System.out.println(username);
        Map<String,String> data = new HashMap<String,String>();
        data.put("username",username);

        return Jwts.builder()
                .setSubject(username)
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .compact();
    }
}