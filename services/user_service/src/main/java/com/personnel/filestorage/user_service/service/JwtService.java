package com.personnel.filestorage.user_service.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

public interface JwtService {
    String extractEmail(String jwt);
    String generateToken(UserDetails userDetails);
    String generateToken(
            HashMap<String, Object> extraClaims,
            UserDetails userDetails
    );
    String buildToken(HashMap<String, Object> extraClaims,
                      UserDetails userDetails,
                      Long expiresIn);
    boolean isTokenValid(String token, UserDetails userDetails);
    boolean isTokenExpired(String token);
    Date extractExpiration(String token);
    <T> T extractClaims(String jwt, Function<Claims, T> claimsResolver);
    Claims extractAllClaims(String token);
    SecretKey getSigningKey();
}
