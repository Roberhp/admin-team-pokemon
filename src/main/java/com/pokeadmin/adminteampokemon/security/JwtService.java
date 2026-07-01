package com.pokeadmin.adminteampokemon.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.pokeadmin.adminteampokemon.config.JwtProperties;
import com.pokeadmin.adminteampokemon.trainer.entity.TrainerEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final JwtProperties jwtProperties;

    public JwtService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(
                jwtProperties.getSecret()
                        .getBytes(StandardCharsets.UTF_8));
    }

    private Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith((javax.crypto.SecretKey) getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateToken(TrainerEntity trainer) {

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + jwtProperties.getExpiration());

        return Jwts.builder()
                .subject(trainer.getTrainerId().toString())
                .claim("username", trainer.getUsername())
                .issuedAt(now)
                .expiration(expirationDate)
                .signWith((javax.crypto.SecretKey) getSigningKey())
                .compact();
    }

    public String extractUsername(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("username", String.class);
    }

    public Long extractTrainerId(String token) {
        Claims claims = extractAllClaims(token);
        
        return Long.valueOf(claims.getSubject());
    }

    public boolean isTokenValid(String token) {
        try {
            Claims claims = extractAllClaims(token);
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public long extractTrainerIdFromHeader (String token){
        return extractTrainerId(extractToken(token));
    }

    public String extractToken(String token){
        return token.substring(7);
    }

}
