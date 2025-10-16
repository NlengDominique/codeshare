package com.lndev.codeshut.service;

import com.lndev.codeshut.entities.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class JwtService {

    private static final String jwtSecreteKey = "ddgdbydjsmsjjsmhdgdndjsksjbdddjdkddk";

    private SecretKey generateSecreteKey(){
        return Keys.hmacShaKeyFor(jwtSecreteKey.getBytes(StandardCharsets.UTF_8));
    }


    //create token method
    public String createToken(String subject) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .subject(subject)
                .claims(claims)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60))
                .signWith(generateSecreteKey())
                .compact();
    }
    public Long generateUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(generateSecreteKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return Long.valueOf(claims.getSubject());

    }

}
