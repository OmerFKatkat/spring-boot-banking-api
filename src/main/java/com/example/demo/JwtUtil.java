package com.example.demo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;


@Component
public class JwtUtil {
    private static final String secret_key = "MySecretKeyForJWTGenerationBank123456";
    public static String generateToken(String email) {
        return String.valueOf(Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plusSeconds(86400)))
                .signWith(
                        SignatureAlgorithm.HS256,
                        secret_key.getBytes(StandardCharsets.UTF_8)
                )
                .compact()
        );
    }

    public static boolean validateToken(String jwt) {
        String email = Jwts.parser()
                .setSigningKey(secret_key.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
        return email != null && !email.isEmpty();
    }
}
