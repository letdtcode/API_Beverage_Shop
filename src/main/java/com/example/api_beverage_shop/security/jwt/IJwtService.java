package com.example.api_beverage_shop.security.jwt;

import com.example.api_beverage_shop.security.CustomUserDetail;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.function.Function;

public interface IJwtService {

    String extractUsername(String token);

    Date extractExpiration(String token);

    String extractRoles(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    Claims extractAllClaims(String token);

    Boolean isTokenExpired(String token);

    String generateToken(UserDetails userDetail);

    String generateRefreshToken(UserDetails userDetail);

    Boolean validateToken(String token, UserDetails userDetails);
}
