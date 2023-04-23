package com.example.onlinecourses.config;

import com.example.onlinecourses.constants.jwt.JwtConstants;
import com.example.onlinecourses.exception.domain.JwtTokenInvalidException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JwtGenerator {
    public String generateToken(Authentication auth){
        String userName =auth.getName();
        Date expireDate=Date.from(ZonedDateTime.now().plusSeconds(JwtConstants.JWT_EXPIRATION).toInstant());
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setIssuer("Lab course")
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, JwtConstants.JWT_SECRET)
                .compact();
    }
    public String getUserNameFromJWT(String token){
        Claims claims=Jwts.parser()
                .setSigningKey(JwtConstants.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    public boolean validateToken(String token){
        try {
            Jwts.parser()
                    .setSigningKey(JwtConstants.JWT_SECRET)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e){
            throw new JwtTokenInvalidException();
        }

    }
}
