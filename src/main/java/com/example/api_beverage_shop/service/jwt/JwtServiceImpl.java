package com.example.api_beverage_shop.service.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class JwtServiceImpl implements IJwtService{
    private String SECRET_KEY = "thisissecret";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractRoles(String token) {
        return (String) extractAllClaims(token).get("roles");
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("roles", userDetails.getAuthorities().toString());
//        return createToken(claims, userDetails.getUsername(), refresh_token);
//        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());
//        return JWT.create()
//                .withSubject(userDetails.getUsername())
//                .withExpiresAt(new Date(System.currentTimeMillis() + 50 * 60 * 1000))
//                .withClaim("roles", userDetails.getAuthorities().stream().toList())
//                .sign(algorithm);
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());
            return JWT.create()
                    .withSubject(userDetails.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 50 * 60 * 1000))
                    .withClaim("roles", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String generateRefreshToken(UserDetails userDetails) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 50 * 60 * 1000))
                .sign(algorithm);
    }

//    public String createToken(Map<String, Object> claims, String subject, Boolean refresh_token) {
//        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(!refresh_token ? new Date(System.currentTimeMillis() + 10 * 60 * 1000)
//                        : new Date(System.currentTimeMillis() + 30 * 60 * 1000))
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
//    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
