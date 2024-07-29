package com.bangvan.apiblogapp.security;

import com.bangvan.apiblogapp.exception.BlogAPIException;
import com.bangvan.apiblogapp.exception.ErrorCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTTokenProvider {
    @Value("${app-jwt-secret}")
    private String jwtSecretKey;
    @Value("${app-jwt-expiration-millisecond}")
    private Long jwtExpirationDate;

    // Generate JWT token
    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime()+jwtExpirationDate);
        String token = Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expirationDate)
                .signWith(key())
                .compact();
        return token;
    }
    private SecretKey key(){
       return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecretKey));

    }

    // Get username from JWT token
    public String  getUsername (String token){
        return Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // Validate JWT Token
    public boolean validateJWTToken(String token){
        try {
            Jwts.parser()
                    .verifyWith(key())
                    .build()
                    .parse(token);
            return true;
        }catch (MalformedJwtException malformedJwtException){
            throw  new BlogAPIException(ErrorCode.TOKEN_INVALID);
        }catch(ExpiredJwtException expiredJwtException){
            throw  new BlogAPIException(ErrorCode.TOKEN_EXPIRED);
        }catch(UnsupportedJwtException unsupportedJwtException){
            throw new BlogAPIException(ErrorCode.TOKEN_UNSUPPORTED);
        }catch(IllegalArgumentException illegalArgumentException){
            throw new BlogAPIException(ErrorCode.TOKEN_CLAIMS_ILLEGAL);
        }

    }
}
